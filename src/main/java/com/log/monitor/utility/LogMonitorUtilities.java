package com.log.monitor.utility;

import com.log.monitor.model.LogModel;

public interface LogMonitorUtilities {

	public static boolean isLogStateNotMatched(LogModel logModel, LogModel logModel2) {
		return null != logModel && null != logModel2 && null != logModel.getState()
				&& !logModel.getState().equals(logModel2.getState());
	}

}
