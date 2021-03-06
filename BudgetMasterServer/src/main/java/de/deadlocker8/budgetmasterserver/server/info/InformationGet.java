package de.deadlocker8.budgetmasterserver.server.info;

import com.google.gson.Gson;

import de.deadlocker8.budgetmaster.logic.serverconnection.ServerInformation;
import de.deadlocker8.budgetmaster.logic.updater.VersionInformation;
import de.deadlocker8.budgetmasterserver.logic.AdvancedRoute;
import de.deadlocker8.budgetmasterserver.logic.Settings;
import spark.Request;
import spark.Response;

public class InformationGet implements AdvancedRoute
{	
	private Gson gson;
	private VersionInformation versionInfo;
	private Settings settings;

	public InformationGet(Gson gson, VersionInformation versionInfo, Settings settings)
	{
		this.gson = gson;
		this.versionInfo = versionInfo;
		this.settings = settings;
	}

	@Override
	public void before()
	{
	}

	@Override
	public Object handleRequest(Request req, Response res)
	{
		ServerInformation serverInfo = new ServerInformation();
		serverInfo.setDatabaseUrl(settings.getDatabaseUrl());
		serverInfo.setDatabaseName(settings.getDatabaseName());
		serverInfo.setDatabaseUsername(settings.getDatabaseUsername());
		serverInfo.setServerPort(settings.getServerPort());
		serverInfo.setKeystorePath(settings.getKeystorePath());
		serverInfo.setVersionInfo(versionInfo);		
		
		return gson.toJson(serverInfo);
	}

	@Override
	public void after()
	{
	}
}