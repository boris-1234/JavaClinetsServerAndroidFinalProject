import java.io.*;

//client tester: http://sockettest.sourceforge.net/
//client server: https://www.geeksforgeeks.org/socket-programming-in-java/
public class ClientHandler implements IHandler {

    private Game game;
    private ObjectInputStream objectInputStream;// for writing to client כותב נתונים ל
    private ObjectOutputStream objectOutputStream;// for reading from client קורא נתונים מ

    public ClientHandler(Game game) {

        this.game = game;
    }


    @Override
    public void resetMembers() {// לא בשימוש
        this.game = null;
    }

    //new user logged in
    @Override
    public void handle(InputStream fromClient, OutputStream toClient,ClientsNumber clientsNumber) throws IOException, ClassNotFoundException {
        this.objectInputStream = new ObjectInputStream(fromClient);// יצירת אובייקט לקריאה מ הclinet
        this.objectOutputStream = new ObjectOutputStream(toClient);//יצירת אובייקט לכתיבה לתוך clinet


           Object o =  recieveObject(objectInputStream.readObject());// קריאה מה clinet של הפקודה יכול להיות i,p,r
           switch (o.toString())
           {
               //Sent to Client his id (send 0 for X,send 1 for O)
               case "i"://Send id// שליחת id לקליינט לפיו הוא יודע איזה שחקן הוא X ואיזה עיגל
                   writeMessage((clientsNumber.getCount())+"");// לשלוח ל clinet את ה id שלו ומקבלים את מספר ה id האחרון בהודעה או שאיקס שיחק אחרון או ש עיגול
                   clientsNumber.addClient();// רישום ה clinet בשרת שבעצם יודעים האם יש 1 או 2 clinets מחוברים
                   break;
                   //client wants to make a move
               case "p"://play
                   String cliendId = objectInputStream.readObject().toString();//read the id of client (0 - X , 1 - O)
                   System.out.println("clientid:"+ cliendId);// מדפיס את מספר ה- Id
                   int index =(int)(objectInputStream.readObject());//read index from client
                   System.out.println("index:"+index);
                   boolean firstPlayer = cliendId .equals("0") ; //if id is 0 - the player is X - firstPlayer = true
                   char result = game.play(firstPlayer,index/3,index%3); //play the client move by the index
                   System.out.println("result:"+result);
                   writeMessage(result+"");

               case "r"://Send the Board to the client
                   for(int i=0;i<3;i++)
                   {
                       for(int j=0;j<3;j++) {
                           if (game.getMat()[i][j] == 'X') {
                               objectOutputStream.writeObject((i*3+j)+","+0);//אם יש איקס תשלח 0
                               continue;
                           }
                           if (game.getMat()[i][j] == 'O') {
                               objectOutputStream.writeObject((i*3+j)+","+1);//אם יש עיגול תשלח אחד

                           }
                           else {
                               objectOutputStream.writeObject((i*3+j)+","+(-1)); //אם לא איקס ולא עיגול תשלח מינוס אחד
                           }

                       }
                   }
                   objectOutputStream.writeObject(game.getWinner());//תשלח האם ומי ניצח  ..//None, -1 - tiko, 0 - x , 1 -circle

                   break;



           }

//        }
    }
    Object recieveObject(Object o) throws IOException {
        System.out.println("recieved object "+o);
        return o;
        //doWork = false;
//        objectInputStream.close();
//        objectOutputStream.close();
    }

    void writeMessage(String msg) throws IOException {
        objectOutputStream.writeObject(msg);
    }

    // played at index, x or o
    void recieveMessage(String msg) {
        System.out.println("recieved: " + msg);
        switch (msg) {
            case "palyed":
                break;
            case "newuser":
                break;
        }
    }
}
