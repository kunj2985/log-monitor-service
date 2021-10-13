package com.log.monitor.constant;

public interface LogMonitorConstants {

	String LOG_ERROR_MESSAGE="Error while processing log file";
	String LOG_ERROR_MESSAGE_FORMAT="Error while processing logger line";
	String SUCCESS_MESSAGE="Event data processed successfully.";
	String SUCCESS_CODE_OK="S200";
	String STATUS_SUCCESS="Success";
	String ERROR_CODE_FILE_NOT_FOUND="E100";
	String ERROR_CODE_FILE_FORMAT="E200";
	String ERROR_CODE_COMMON="E500";
	String STATUS_FAILURE="Failure";
	String LOG_FILE_PATH_KEY="log.file.path";
	
	
	String LOGGER_MSG_FILE_NOT_FORMATTED="Log file not properly structured or error in format.";
	String LOGGER_MSG_LOG_LINE_PROCESSED="Log line has been processed succesfully: "; 
	String LOGGER_MSG_ALL_LOGS_PROCESSED="All loggers have been processed successfully...";
	String LOGGER_MSG_LINE_PROCESSING_WITH_ID="Logger line being processing- Id: ";
	String LOGGER_MSG_LOG_LINE_PROCESSING="Log lines strat processing...";
	String LOGGER_MSG_LOG_FILE_PROCESSING="Log file strat processing...";
	String LOGGER_MSG_EVENT_DATA_INSERTED="Event data inserted in database";
	String LOGGER_MSG_FILE_NOT_FOUND="Log file not found.";
	String LOG_ERROR_MESSAGE_FOLDER_NOT_FOUND = "Log Folder not found.";
	String UNEXPECTED_ERROR = "Unexpected Error ocurred.";
}
