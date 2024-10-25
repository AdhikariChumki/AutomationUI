package utility;

import configuration.Base;

public class ConfigFileReader extends Base {

	public String getReportConfigPath(){
		
		String reportConfigPath=System.getProperty("user.dir")+"/config/extent-config.xml";
		return reportConfigPath;		 
		}

}
