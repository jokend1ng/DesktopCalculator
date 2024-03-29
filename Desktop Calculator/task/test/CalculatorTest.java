import calculator.Calculator;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.exception.outcomes.WrongAnswer;
import org.hyperskill.hstest.stage.SwingTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.swing.SwingComponent;

import java.util.Map;

import static java.util.Map.entry;
import static org.hyperskill.hstest.testcase.CheckResult.correct;

public class CalculatorTest extends SwingTest {
    private Map<Character, JButtonFixture> charToButtonMap;

    @SwingComponent(name = "Equals")
    private JButtonFixture mEqual;
    @SwingComponent(name = "Add")
    private JButtonFixture mAdd;
    @SwingComponent(name = "Subtract")
    private JButtonFixture mSub;
    @SwingComponent(name = "Divide")
    private JButtonFixture mDiv;
    @SwingComponent(name = "Multiply")
    private JButtonFixture mMult;
    @SwingComponent(name = "Zero")
    private JButtonFixture mZero;
    @SwingComponent(name = "One")
    private JButtonFixture mOne;
    @SwingComponent(name = "Two")
    private JButtonFixture mTwo;
    @SwingComponent(name = "Three")
    private JButtonFixture mThree;
    @SwingComponent(name = "Four")
    private JButtonFixture mFour;
    @SwingComponent(name = "Five")
    private JButtonFixture mFive;
    @SwingComponent(name = "Six")
    private JButtonFixture mSix;
    @SwingComponent(name = "Seven")
    private JButtonFixture mSeven;
    @SwingComponent(name = "Eight")
    private JButtonFixture mEight;
    @SwingComponent(name = "Nine")
    private JButtonFixture mNine;

    @SwingComponent(name = "EquationTextField")
    private JTextComponentFixture mEquation;


    public CalculatorTest() {

        super(new Calculator());
    }

    private void typeText(String text, String expectedResult) {
        JButtonFixture button = null;
        for (int i = 0; i < text.length(); i++) {
            button = charToButtonMap.get(text.charAt(i));
            button.click();
        }
        try {
            if (!mEquation.text().trim().equals(expectedResult)) {
                assert button != null;
                if (!button.text().matches("\\d")) {
                    throw new WrongAnswer("EquationTextField contains wrong operator.\n" +
                        "    Your output: " + mEquation.text().trim() + "\n" +
                        "Expected output: " + expectedResult);
                } else {
                    throw new WrongAnswer("EquationTextField contains wrong number.\n" +
                        "    Your output: " + mEquation.text().trim() + "\n" +
                        "Expected output: " + expectedResult);
                }

            }
        } catch (NullPointerException e) {
            throw new WrongAnswer("EquationTextField is empty.");
        }
        requireEnabled(mEquation);
        mEquation.setText("");
    }


    @DynamicTest
    CheckResult test1 () {
        charToButtonMap = Map.ofEntries(
            entry('0', mZero),
            entry('1', mOne),
            entry('2', mTwo),
            entry('3', mThree),
            entry('4', mFour),
            entry('5', mFive),
            entry('6', mSix),
            entry('7', mSeven),
            entry('8', mEight),
            entry('9', mNine),
            entry('+', mAdd),
            entry('-', mSub),
            entry('*', mMult),
            entry('/', mDiv),
            entry('=', mEqual)
        );

        requireEnabled(mEqual, mAdd, mSub, mDiv, mMult, mOne, mTwo, mThree, mFour, mFive, mSix,
            mSeven, mEight, mNine, mZero);

        requireVisible(mEqual, mAdd, mSub, mDiv, mMult, mOne, mTwo, mThree, mFour, mFive, mSix,
            mSeven, mEight, mNine, mZero);

        return correct();
    }

    // Pushing buttons
    @DynamicTest()
    CheckResult test2 () {

        typeText("1", "1");
        typeText("2", "2");
        typeText("3", "3");
        typeText("4", "4");
        typeText("5", "5");
        typeText("6", "6");
        typeText("7", "7");
        typeText("8", "8");
        typeText("9", "9");
        typeText("0", "0");
        typeText("+", "+");
        typeText("-", "-");
        typeText("*", "x");
        typeText("/", "/");

        return correct();
    }

    //Testing calculations
    @DynamicTest()
    CheckResult test3 () {

        //Add operation
        typeText("1+1=", "1+1=2");
        typeText("9+1=", "9+1=10");
        typeText("9999+1=", "9999+1=10000");
        typeText("599+699=", "599+699=1298");


        return correct();
    }

    @DynamicTest()
    CheckResult test4 () {
        //Subtract operations
        typeText("1-1=", "1-1=0");
        typeText("99-1=", "99-1=98");
        typeText("12-7=", "12-7=5");

        return correct();
    }

    @DynamicTest()
    CheckResult test5 () {
        //Multiply operations
        typeText("1*1=", "1x1=1");
        typeText("99*3=", "99x3=297");
        typeText("12*0=", "12x0=0");
        typeText("243*13=", "243x13=3159");

        return correct();
    }

    @DynamicTest()
    CheckResult test6 () {
        //Divide operations
        typeText("1/1=", "1/1=1");
        typeText("9/3=", "9/3=3");
        typeText("81/9=", "81/9=9");

        return correct();
    }

}