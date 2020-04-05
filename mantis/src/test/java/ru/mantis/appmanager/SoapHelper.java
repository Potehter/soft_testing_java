package ru.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.mantis.model.Issue;
import ru.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {
    private final ApplicationManager app;
    private String login;
    private String password;
    public SoapHelper(ApplicationManager app) {
        this.app = app;
        login = app.getProperty("web.login");
        password = app.getProperty("web.password");
    }

    public Set<Project> getProjects() throws RemoteException, MalformedURLException, ServiceException {
        MantisConnectPortType mc = getMantisConnect();
        ProjectData[] projects = mc.mc_projects_get_user_accessible(login, password);
        return Arrays.asList(projects).stream().map((p) -> new Project()
                .withId(p.getId().intValue()).withName(p.getName()))
                .collect(Collectors.toSet());
    }

    private MantisConnectPortType getMantisConnect() throws MalformedURLException, ServiceException {
        return new MantisConnectLocator().
                getMantisConnectPort(new URL(app.getProperty("wsdl.path")));
    }

    public Issue addIssue(Issue issue) throws RemoteException, MalformedURLException, ServiceException {
        MantisConnectPortType mc = getMantisConnect();
        String[] categories = mc.mc_project_get_categories(login, password,
                BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        issueData.setCategory(categories[0]);
        BigInteger issueId = mc.mc_issue_add(login, password, issueData);
        IssueData createdIssueData = mc.mc_issue_get(login, password, issueId);
        return new Issue().withId(createdIssueData.getId().intValue())
                .withSummary(createdIssueData.getSummary()).withDescription(createdIssueData.getDescription())
                .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                                          .withName(createdIssueData.getProject().getName()));
    }
}
