//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * This class handles Matrix-related tasks
// */
//public class MatrixIHandler implements IHandler {
//
//    private Matrix matrix;
//    private Index startIndex;
//    private Index endIndex;
//    private volatile boolean doWork = true;
//    private ObjectInputStream objectInputStream;
//    private ObjectOutputStream objectOutputStream;
//
//    @Override
//    public void resetMembers() {
//        this.matrix = null;
//        this.startIndex = null;
//        this.endIndex = null;
//        this.doWork = true;
//    }
//
//    @Override
//    public void handle(InputStream fromClient, OutputStream toClient) throws IOException, ClassNotFoundException {
//        this.objectInputStream = new ObjectInputStream(fromClient);
//        this.objectOutputStream = new ObjectOutputStream(toClient);
//        boolean doWork = true;
//        while (doWork) {
//            recieveMessage(objectInputStream.readObject().toString());
//        }
//    }
//
//    void writeMessage(String msg) throws IOException {
//        objectOutputStream.writeUTF(msg);
//    }
//
//    void recieveMessage(String msg) {
//        switch () {
//            case "palyed":
//                break;
//            case "newuser":
//                break;
//        }
//    }
//}
