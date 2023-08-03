package com.nttdata.terpel.msconsultalistavehiculos.processors;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.bigquery.*;
import com.nttdata.terpel.commons.liblog.configuration.LoggerPrinter;
import com.nttdata.terpel.commons.liblog.util.ProcessType;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;

import static com.nttdata.terpel.commons.liblog.util.LoggerConstant.VALOR_NA;
import static com.nttdata.terpel.msconsultalistavehiculos.model.constant.Constantes.CONSULTA;
import static com.nttdata.terpel.msconsultalistavehiculos.model.constant.Constantes.JOB_NO_EXISTE;


@Component
public class BigQuerySQL {

    @Value("${spring.cloud.gcp.credentials.location}")
    private String credentialsPath;

    @Value("${data.projectId}")
    private String project;

    @SneakyThrows
    public TableResult proceso(String consulta, LoggerPrinter loggerPrinter) {

        loggerPrinter.log(LogLevel.INFO, "Consumo consulta bigQuery",
                CONSULTA + consulta, VALOR_NA, VALOR_NA, ProcessType.PROCESO);
        String projectId = this.project;
        File credentialsPathFile = ResourceUtils.getFile(this.credentialsPath);

        // Load credentials from JSON key file. If you can't set the GOOGLE_APPLICATION_CREDENTIALS
        // environment variable, you can explicitly load the credentials file to construct the
        // credentials.
        GoogleCredentials credentials;
        try (FileInputStream serviceAccountStream = new FileInputStream(credentialsPathFile)) {
            credentials = ServiceAccountCredentials.fromStream(serviceAccountStream);
        }
        BigQuery bigquery =
                BigQueryOptions.newBuilder()
                        .setCredentials(credentials)
                        .setProjectId(projectId)
                        .build()
                        .getService();

        QueryJobConfiguration queryConfig =
                QueryJobConfiguration.newBuilder(consulta)
                        // Use standard SQL syntax for queries.
                        // See: https://cloud.google.com/bigquery/sql-reference/
                        .setUseLegacySql(false)
                        .build();

        // Create a job ID so that we can safely retry.
        JobId jobId = JobId.of(UUID.randomUUID().toString());
        Job queryJob = bigquery.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());

        // Wait for the query to complete.
        queryJob = queryJob.waitFor();

        // Check for errors
        if (queryJob == null) {
            throw new BigQuerySQLException(JOB_NO_EXISTE);
        } else if (queryJob.getStatus().getError() != null) {
            // You can also look at queryJob.getStatus().getExecutionErrors() for all
            // errors, not just the latest one.
            throw new BigQuerySQLException(queryJob.getStatus().getError().toString());
        }

        loggerPrinter.log(LogLevel.INFO, "Retorno data de consulta BigQuery",
                queryJob.toString(), VALOR_NA, VALOR_NA, ProcessType.PROCESO);
        // Get the results.
        return queryJob.getQueryResults();

    }
}
