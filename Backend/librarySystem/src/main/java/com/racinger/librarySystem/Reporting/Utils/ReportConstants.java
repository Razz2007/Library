package com.racinger.librarySystem.Reporting.Utils;

public class ReportConstants {

    // Report types
    public static final String REPORT_TYPE_GENERAL = "GENERAL";
    public static final String REPORT_TYPE_BOOKS = "BOOKS";
    public static final String REPORT_TYPE_STUDENTS = "STUDENTS";
    public static final String REPORT_TYPE_LOANS = "LOANS";
    public static final String REPORT_TYPE_PENALTIES = "PENALTIES";
    public static final String REPORT_TYPE_OVERDUE = "OVERDUE";

    // Date formats
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    // Report messages
    public static final String REPORT_GENERATED_SUCCESSFULLY = "Reporte generado exitosamente";
    public static final String NO_DATA_FOUND = "No se encontraron datos para el período especificado";

    private ReportConstants() {
        // Utility class
    }
}