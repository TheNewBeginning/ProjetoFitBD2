package objectTransferData;

public class ObjectData {

    private static String msg;

    public static void SendToMsg(String msg) {
        ObjectData.msg = msg;
        System.out.println("STATIC:" + ObjectData.msg);
    }

    public static String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        ObjectData.msg = msg;
    }

}
