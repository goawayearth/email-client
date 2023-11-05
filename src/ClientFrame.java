import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClientFrame extends JFrame implements ActionListener {
    //主面板
    private JPanel jPanel0=new JPanel();
    private boolean flag=true;
    private JLabel ToAddress=new JLabel("发送给：");
    private JTextField receiveAddress=new JTextField();
    private String filepathone="";
    //主题窗口
    private JLabel titleLabel=new JLabel("主题：");
    private JTextField titleField=new JTextField();
    //内容窗口
    private JTextArea ContentArea=new JTextArea(100,30);
    private JScrollPane ContentPane=new JScrollPane(ContentArea);
    private JFileChooser fileChooser=new JFileChooser();
    //发送按钮
    private JButton centButton=new JButton("发送");
    private final JButton selectFileButton=new JButton("增加附件");
    private final JButton  cancelCent=new JButton("取消");

    public ClientFrame()
    {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setLocation(100,50);
        this.add(jPanel0);
        this.setTitle("邮件客户端");
        jPanel0.setLayout(null);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);//设置只能选择文件
        jPanel0.add(titleField);

        titleField.setLocation(100,30);
        titleField.setSize(600,20);
        jPanel0.add(titleLabel);
        titleLabel.setLocation(10,30);
        titleLabel.setSize(80,20);
        jPanel0.add(ContentPane);
        ContentPane.setSize(600,380);
        ContentPane.setLocation(100,100);
        jPanel0.add(centButton);
        centButton.setSize(100,30);
        centButton.setLocation(600,500);
        jPanel0.add(selectFileButton);
        selectFileButton.setSize(100,30);
        selectFileButton.setLocation(100,500);
        jPanel0.add(cancelCent);
        cancelCent.setSize(100,30);
        cancelCent.setLocation(300,500);
        jPanel0.add(ToAddress);
        ToAddress.setLocation(10,60);
        ToAddress.setSize(80,20);
        receiveAddress.setSize(600,20);
        receiveAddress.setLocation(100,60);
        jPanel0.add(receiveAddress);
        /*
        String receiveMail,  Title, filename, iconName, Content
         */
        cancelCent.addActionListener(this);
        centButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String title=titleField.getText();//标题
                        String Content=ContentArea.getText();//内容
                        String Address=receiveAddress.getText();//目的地址
                        String path=filepathone;
                        try{
                            new MailCore(Address,title,path,Content);
                        }
                        catch(Exception ev){}

                    }
                }
        );
        selectFileButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int flag=fileChooser.showOpenDialog(jPanel0);
                        if(flag==JFileChooser.APPROVE_OPTION)
                        {
                            filepathone=fileChooser.getSelectedFile().getPath();
                        }
                    }
                }

        );

        if(flag)
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        this.setVisible(false);
    }
}
