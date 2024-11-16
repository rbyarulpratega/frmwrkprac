package datafactory;

public class HardCodedDataProvider implements DataProvider  {

	@Override
	public Object[][] setDataProvider() {
		// TODO Auto-generated method stub
		 return new Object[][] {
             {"testleafhardcoded", "password1"}
            };
	}

}
