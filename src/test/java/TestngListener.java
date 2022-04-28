import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

    public class TestngListener extends Base implements ITestListener
    {
        Extent_Report e = new Extent_Report();
        ExtentReports extent=e.ReportGenerator();
        ExtentTest test;
        private static ThreadLocal<ExtentTest> extenttest=new ThreadLocal<ExtentTest>();


        @Override
        public void onTestStart(ITestResult result)
        {
            log.info("Test has started"+result);
            test=extent.createTest(result.getName());
            extenttest.set(test);
        }

        @Override
        public void onTestSuccess(ITestResult result)
        {
            log.info("PASSED TEST "+result);
            extenttest.get().log(Status.PASS,"Successful");


        }

        @Override
        public void onTestFailure(ITestResult result)
        {
            log.info("FAILED TEST "+result);
            extenttest.get().log(Status.FAIL,result.getThrowable());
        }
        @Override
        public void onFinish(ITestContext context) {
            ITestListener.super.onFinish(context);
            extent.flush();
        }

            /* compiled code */
        }


