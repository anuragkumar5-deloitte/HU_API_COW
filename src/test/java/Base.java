import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;

@Listeners(TestngListener.class)
public class Base {
    public static final Logger log = LogManager.getLogger(Base.class);
}
