import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
计划这个页面上放一些个人信息和励志图片
 */
public class MainFrame extends JFrame implements ActionListener{
    private JDialog logFrame=new JDialog(this,"登录",true);
    private final JButton writeMail=new JButton("写邮件");
    private final JButton receiveMail=new JButton("收件箱");
   private JPanel jPanel=new JPanel();
   private final JLabel logName=new JLabel("账号：");
   private final JLabel logpassword=new JLabel("密码：");
   private JTextField Account=new JTextField();
   private JTextField password=new JTextField();
   private final JButton cancelButton=new JButton("取消");
   private final JButton sureButton=new JButton("确定");
    boolean flag=false;
    private JLabel reflex=new JLabel("");

    MainFrame()
    {
        this.setTitle("Java邮箱");
        this.setSize(400,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(200,50);

        setLogFrame();

        writeMail.addActionListener(this);
        receiveMail.addActionListener(this);

        this.add(jPanel);
        jPanel.setLayout(null);
        jPanel.add(writeMail);
        writeMail.setSize(80,20);
        writeMail.setLocation(20,30);
        jPanel.add(receiveMail);
        receiveMail.setSize(80,20);
        receiveMail.setLocation(20,80);
        if(flag)
           this.setVisible(true);
        else
            System.exit(0);
    }

    void setLogFrame()
    {

        reflex.setText("");
        logFrame.setLayout(null);

        logFrame.setSize(400,300);
        logFrame.setLocation(200,50);
        logFrame.add(reflex);
        reflex.setSize(250,20);
        reflex.setLocation(130,150);
        logFrame.add(logName);
        logName.setSize(80,20);
        logName.setLocation(10,20);
        logFrame.add(logpassword);
        logpassword.setSize(80,20);
        logpassword.setLocation(10,60);
        logFrame.add(Account);
        Account.setSize(250,20);
        Account.setLocation(95,20);
        Account.setText("");
        logFrame.add(password);
        password.setSize(250,20);
        password.setLocation(95,60);
        password.setText("");
        logFrame.add(cancelButton);
        logFrame.add(sureButton);
        cancelButton.setSize(80,20);
        cancelButton.setLocation(80,110);
        sureButton.setSize(80,20);
        sureButton.setLocation(220,110);

        cancelButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        logFrame.setVisible(false);
                        System.exit(0);
                    }
                }
        );

        sureButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String acc=Account.getText();
                        String pass=password.getText();
                       // if(acc.equals("17363045571@163.com")&&pass.equals("12345678"))
                        if(acc.equals("8208210620")&&pass.equals("123456"))
                        {
                            flag=true;
                            logFrame.setVisible(false);
                        }
                        else {
                            reflex.setText("账号或密码错误！");
                        }
                    }
                }
        );

        logFrame.setVisible(true);

    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==writeMail)
        {
            new ClientFrame();
        }
        else if(e.getSource()==receiveMail)
        {
            try{
               new RecieveFrame();
            }catch(Exception ev){}
        }
    }
    public static void main(String[] args)
    {
        new MainFrame();
    }
}
