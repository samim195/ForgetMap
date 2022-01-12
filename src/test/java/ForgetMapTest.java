import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ForgetMapTest {

    @Test
    public void whenMapExistsAddAndFindValueFromMap() {
        //When
        ForgetMap forgetMap = new ForgetMap(3);
        String numberPlate = "DV21 AMG";
        String carModel = "E63 AMG";

        //Then
        forgetMap.add(numberPlate, carModel);

        //Verify
        assertEquals(carModel, forgetMap.find(numberPlate));
    }

    @Test
    public void whenMapExistsAndFindMethodIsCalledAssociatedMapCountValueShouldBeIncremented() {
        //When
        ForgetMap forgetMap = new ForgetMap(3);
        String numberPlate = "DV21 AMG";
        String carModel = "E63 AMG";

        //Then
        forgetMap.add(numberPlate, carModel);
        forgetMap.find(numberPlate);

        //Verify
        assertEquals(1, forgetMap.getAssociationMapCount().get(numberPlate));
    }

    @Test
    public void whenNumOfValuesInMapExceedLimitShouldReturnLowestUsage() {
        //When
        ForgetMap forgetMap = new ForgetMap(3);
        forgetMap.add("DV21 AMG", "E63 AMG");
        forgetMap.add("AT19 OPY", "4 Series BMW");
        forgetMap.add("SK17 MN1", "A3 Audi");

        //Then
        forgetMap.find("DV21 AMG");
        forgetMap.find("AT19 OPY");
        forgetMap.find("AT19 OPY");

        //Verify
        assertEquals(2, forgetMap.getAssociationMapCount().get("AT19 OPY"));
    }

    @Test
    public void whenNumOfValuesExceedsLimitAndNewValueIsAddedLeastUsedValueIsRemoved() {
        //When
        ForgetMap forgetMap = new ForgetMap(3);
        forgetMap.add("DV21 AMG", "E63 AMG");
        forgetMap.add("AT19 OPY", "4 Series BMW");
        forgetMap.add("SK17 MN1", "A3 Audi");
        //Then
        forgetMap.find("DV21 AMG");
        forgetMap.find("AT19 OPY");
        forgetMap.find("AT19 OPY");
        forgetMap.add("MT71 TYS", "Model 3 Tesla");

        //Verify
        assertFalse(forgetMap.mapContainsValue("SK17 MN1"));
        assertTrue(forgetMap.mapContainsValue("MT71 TYS"));
    }

}
