package objectTransferData;

import ADO.ConexaoAdo;

public class ObjectData {

    private static String msg;
    //private ConexaoAdo cone = null;
    private static String vazio;

    public static void SendToMsg(String msg) {
        ObjectData.msg = msg;
        System.out.println("STATIC:" + ObjectData.msg);
    }

    public static String getVazio() {
        return vazio;
    }

    public static void setVazio(String vazio) {
        ObjectData.vazio = vazio;
    }

    public static void SendtoMsgEmpty(String vazio) {
        ObjectData.vazio = vazio;
        System.out.println("dado vazio:");
    }

    public static String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        ObjectData.msg = msg;
    }

}
