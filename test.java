package SS;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.Label;
import org.eclipse.egit.github.core.RepositoryIssue;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.IssueService;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IssuesGit {
    public static void main(String[] args) {

        GitHubClient client = new GitHubClient();
        client.setOAuth2Token("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"); // Use the token generated above
        IssueService issueService = new IssueService(client);
        try {
            Issue issue = new Issue();
            issue.setTitle("Test issue"); // Title of the issue
            issue.setBody("Some stuff"); // Body of the issue
            issue.setUrl("dsasad");
            issue.setBodyText("adsad");
            issue.setBody("dsa");
            List<Label> label = new ArrayList<>();
            Label label1 = new Label();

            label1.setName("adsad");
            label.add(label1);

            issue.setLabels(label);
            issue.setBodyHtml("dsadasd");
            File myfile = new File("theDir", "testfile2.txt");
            issue.setBodyHtml("http://192.168.110.110:8091/ui/index.html#/docs/editor?commonBucket=trident_report&commonScope=_default&scenarioZoom=minute&scenario=5sovyu87k");
            issueService.createIssue("prguptadev", "mass360", issue);
            List<RepositoryIssue> list = issueService.getIssues();
        } catch (Exception e) {
            System.out.println("Failed");
            e.printStackTrace();
        }
    }
}
