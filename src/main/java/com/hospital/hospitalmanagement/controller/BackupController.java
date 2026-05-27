package com.hospital.hospitalmanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController

public class BackupController {

    @GetMapping("/backupDatabase")

    public String backupDatabase() {

        try {

            /* BACKUP FOLDER */

            String backupFolder =
                    "C:/hospital_backups/";

            File folder =
                    new File(backupFolder);

            if(!folder.exists()) {

                folder.mkdirs();

            }

            /* TIMESTAMP */

            String timestamp =
                    new SimpleDateFormat(
                            "yyyyMMdd_HHmmss"
                    ).format(new Date());

            /* BACKUP FILE */

            String backupFile =
                    backupFolder
                    + "hospital_backup_"
                    + timestamp
                    + ".sql";

            /* PROCESS BUILDER */

            ProcessBuilder processBuilder =
                    new ProcessBuilder(

                            "C:/Program Files/MySQL/MySQL Server 8.0/bin/mysqldump.exe",

                            "-u",
                            "root",

                            "-pS@meer^14",

                            "hospital_db",

                            "--result-file=" + backupFile

                    );

            Process process =
                    processBuilder.start();

            int processComplete =
                    process.waitFor();

            if(processComplete == 0) {

                return "Database Backup Created Successfully: "
                        + backupFile;

            }

            else {

                return "Backup Failed";

            }

        }

        catch(Exception e) {

            return "Error: "
                    + e.getMessage();

        }

    }

}