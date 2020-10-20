package runner;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//jeżeli pokazuje mi że wciąż feature file nie jest zdefiniowane, zwroć uwagę na ściezkę gdzie sie znajdują (mają byc pod java)
//@RunWith(Cucumber.class) //obowiązkowe, jezeli puszczam przez jUnit, jezeli przez testNg, to rozszerzam klasę AbstractTestNGCucumberTests
@CucumberOptions( //ustawienia dla testu
        features = "src/test/java/features/Login.feature", //położenie feature files
        glue = "steps", //połozenie step files
        dryRun = false, //sprawdza czy feature file pasuje do steps (true- nie wykonuje przy tym testu)
        monochrome = true, //odpowiada za czytelność tekstu ktory pojawia sie w konsoli
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"} //odpowiada za tworzenie raportu
        //html - odpowiada za generowanie raportu html w odpowiedniej lokalizacji
        //json generuje raport w formacie json

)


public class TestRunner extends AbstractTestNGCucumberTests {
}
