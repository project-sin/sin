package sin.sin.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sin.sin.aws.AwsS3Config;
import sin.sin.aws.S3Util;

@SpringBootTest
public class S3UtilTest {

    @Autowired
    private AwsS3Config awsS3Config;

    @Test
    void 이미지_URL_조회() {
        S3Util s3Util = new S3Util(awsS3Config.amazonS3Client(), awsS3Config.getBucket());
        String test = s3Util.getFileURL("EventList1.PNG");
        System.out.println(test);
    }
}
