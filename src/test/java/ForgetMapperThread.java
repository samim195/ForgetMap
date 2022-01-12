public class ForgetMapperThread extends Thread {

    private ForgetMap forgetMap;

    public ForgetMapperThread(ForgetMap forgetMap) {
        this.forgetMap = forgetMap;
    }

    @Override
    public void run() {
        for(int i=0; i < 10; i++) {
            forgetMap.add("DV21 AMG", "E63 AMG");
            forgetMap.add("AT19 OPY", "4 Series BMW");
            forgetMap.add("SK17 MN1", "A3 Audi");

            forgetMap.find("DV21 AMG");
            forgetMap.find("AT19 OPY");
            forgetMap.find("AT19 OPY");

        }
    }
}
