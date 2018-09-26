set XJC_RUN=java -Dfile.encoding=UTF-8 -cp "%JAVA_HOME%"\lib\tools.jar com.sun.tools.internal.xjc.Driver

%XJC_RUN% SearchDetail.xsd -d src -p com.founder.smartcity.core.model.search
%XJC_RUN% EditDetail.xsd -d src -p com.founder.smartcity.core.model.edit

pause;