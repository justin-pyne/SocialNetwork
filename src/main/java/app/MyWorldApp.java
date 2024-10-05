package app;

import javax.swing.*;

/** The "main" class for the MyWorld app */
public class MyWorldApp {
    private SocialNetwork socialNetwork = new SocialNetwork();
    private JFrame frame1;
    private JFrame frame2;
    private ProfileFactoryInterface profileFactory = new ProfileFactory();
    private SocialNetworkController controller = new SocialNetworkController(socialNetwork);

    // FILL IN CODE: Add a method to set up the project (load data etc).
    private void loadData(String fileName, ProfileFactoryInterface factory){
        socialNetwork.loadJson(fileName, factory);
    }

    /**
     * Creates a GUI window (JFrame) which contains MyPanel
     * @return window (JFrame)
     */
    public JFrame createFrame() {
        JFrame frame = new JFrame ("MyWorld");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        JPanel panel = new MainPanel(socialNetwork, controller);
        // FILL IN CODE: a panel should register as an Observer for the social network
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        socialNetwork.registerObserver((Observer)panel);
        return frame;
    }

    /** Create two GUI windows (both will be run in the same EDT thread)
     */
    public void initializeFrames() {
        // Event dispatch thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // create two frames
                frame1 = createFrame();
                frame2 = createFrame();
            }
        });
    }


    public static void main(String[] args) {
        MyWorldApp app = new MyWorldApp();
        // FILL IN CODE:  load data
        app.loadData("./profiles.json", app.profileFactory);
        app.initializeFrames();
    }

}

