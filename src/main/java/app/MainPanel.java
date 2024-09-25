package app;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/** A GUI for the project */
public class MainPanel extends JPanel {
	// FILL IN CODE: A panel must implement Observer interface, so that it is an observer for the SocialNetwork,
	// and gets notified when something changes in the social network
	private SocialNetwork socialNetwork;
	private String loggedInName = " "; // the user or organization who is currently logged in

	private static String defaultPathToSaveSocialNetwork = "savedProfiles.json";
	// the text field and the button used to log in
	private JTextField loginUserName;
	private JButton loginUserButton;

	// the text field for entering the password
	private JPasswordField loginUserPassword;
	private JButton saveButton; // for saving the social network to the json file

	// the text field and the button used to add a new post (for users)
	private JTextField newPost;
	private JButton addNewPostButton;

	// the text field and the button used to add a new event (for organizations)
	private JTextField newEvent;
	private JButton addNewEventButton;

	// the text field and the button used to add a new friend
	private JTextField friend;
	private JButton addNewFriendButton;
	private JButton removeFriendButton;

	// subpanels of MainPanel
	private JPanel topPanel; // the blue panel on top
	private JPanel infoPanel; // shows profile of the person or organization (this panel also consists of subpanels)

	/**
	 * Constructor
	 * @param socialNetwork reference to SocialNetwork
	 */
	public MainPanel(SocialNetwork socialNetwork) {
		this.socialNetwork = socialNetwork;
		setPreferredSize(new Dimension(500, 500));
		setBackground(Color.lightGray);
		setLayout(new BorderLayout());
		createPanels();
	}

	/**
	 * Creates all panels
	 */
	public void createPanels() {
		createTopPanel();
		createInfoPanel();
		loggedInName = "Test"; // TODO: change
		// FILL IN CODE:
		// Set the loggedInName to some "default" user (first user in your social network?)
		// Call a method to show the profile
		showUserProfile();
		add(topPanel, BorderLayout.NORTH);
		add(infoPanel, BorderLayout.CENTER);
	}

	/**
	 * Creates the top (blue) panel that is used for logging in or saving
	 * profiles.
	 **/
	private void createTopPanel() {
		topPanel = new JPanel();
		topPanel.setBackground(new Color(0, 0, 100));

		loginUserName = new JTextField(5);
		topPanel.add(loginUserName, LEFT_ALIGNMENT);
		loginUserPassword = new JPasswordField(5);
		loginUserPassword.setEchoChar('*');
		topPanel.add(loginUserPassword, LEFT_ALIGNMENT);

		loginUserButton = new JButton("Login");
		loginUserButton.addActionListener(new ButtonListener());
		topPanel.add(loginUserButton, LEFT_ALIGNMENT);

		saveButton = new JButton("Save Profiles");
		saveButton.addActionListener(new ButtonListener());
		topPanel.add(saveButton, LEFT_ALIGNMENT);
		this.add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * A panel that contains person's or organization's info including the
	 * newsfeed.
	 */
	private void createInfoPanel() {
		infoPanel = new JPanel();
		infoPanel.setPreferredSize(new Dimension(450, 200));
		infoPanel.setBackground(Color.white);
		infoPanel.setLayout(new BorderLayout());
	}

	/**
	 * A helper method that adds an image from a given file to the given panel.
	 * Assumes images are in the /images subfolder in your project directory.
	 */
	public void addImage(String imageFile, JPanel imagePanel) {
		File imFile = new File("images" + File.separator + imageFile);
		BufferedImage myPicture;
		try { // read image and add it to the panel
			myPicture = ImageIO.read(imFile);
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			picLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
			imagePanel.add(picLabel, BorderLayout.WEST);
		} catch (IOException e) {
			System.out.println("Could not load an image: " + e);
		}
	}

	/**
	 * A helper method for adding a text label to the given panel
	 */
	public void addLabel(String name, String font, int fontSize, JPanel panel) {
		JLabel text = new JLabel(name);
		text.setFont(new Font(font, Font.PLAIN, fontSize));
		text.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(text);
	}

	/**
	 * A helper method to create a button with the icon on top of it. Use it to
	 * create buttons for adding a friend and removing a friend. The file with
	 * the image should be in the same folder as .class files.
	 */
	public JButton createButtonWithIcon(String iconFile) {
		JButton buttonWithIcon = new JButton();
		buttonWithIcon.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true)); // Adjust color and thickness
		try {
			Image img = ImageIO.read(new File("images" + File.separator + iconFile));
			buttonWithIcon.setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
			img.flush();
			buttonWithIcon.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println("Could not load an icon from the image: " + ex);
		}
		buttonWithIcon.addActionListener(new ButtonListener());
		buttonWithIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
		return buttonWithIcon;
	}

	/**
	 * An inner class. A Listener for all the buttons
	 */
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if (b.equals(loginUserButton)) {
				System.out.println("Login button pressed.");
				loggedInName = loginUserName.getText();
				char[] password = loginUserPassword.getPassword();
				// FILL IN CODE: Call a method on the social network to authenticate the user
				// FILL IN CODE: should show a user or an organization profile

			} else if (b.equals(addNewPostButton)) { // user entered a new post
				String newMessage = newPost.getText();
				// FILL IN CODE:
				// Create/Add a post for the user/organization who is logged in
				// You need to call the method on the social network
				// Call the method to show an updated profile
				// Decide if you need to invoke any other methods

			} else if (b.equals(addNewFriendButton)) { // user added a new
				// friend
				String friendName = friend.getText();
				// FILL IN CODE: add a new friend for the logged-in user
				// Call the method to show an updated profile
				// Decide if you need to invoke any other methods

			} else if (b.equals(removeFriendButton)) { // user removed a friend
				String friendName = friend.getText();
				// FILL IN CODE: remove a friend of the logged-in user
				// Call the method to show an updated profile
				// Decide if you need to invoke any other methods

			} else if (b.equals(addNewEventButton)) {
				String eventText = newEvent.getText();
				// FILL IN CODE:
				// Add a new even for the logged-in organization
				// You can assume it is the same as adding a post.
				// Call the method to show an updated profile
				// Decide if you need to invoke any other methods

			} else if (b.equals(saveButton)) {
				// FILL IN CODE:
				// Save social network in the file with the name
				// stored in defaultPathToSaveSocialNetwork
			}
			updateUI();
		}
	}

	/**
	 * Shows a user profile with some hardcoded values.
	 * You need modify this method to display the user profile with name = loggedInName.
	 * Removes everything from infoPanel and then rebuilds it again.
	 * Think of how you can write a similar method to display an organization profile.
	 */
	public void showUserProfile() {
		infoPanel.removeAll();
		JPanel profilePanel = new JPanel();
		profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));

		// Panel to display the image
		JPanel imageNamePanel = new JPanel();
		imageNamePanel.setPreferredSize(new Dimension(700, 70));
		String imageFile = "no_image.png"; // TODO: replace with the image of the logged-in user
		addImage(imageFile, imageNamePanel);
		addLabel(loggedInName, "Serif", 20, imageNamePanel);

		// Panel to show friends
		JPanel showFriendsPanel = new JPanel();
		String friendsString = "Friend1, Friend2, Friend3"; // TODO: change to friends of the logged-in user
		addLabel("Friends: " + friendsString, "Serif", 15, showFriendsPanel);

		// Panel to add a new friend
		JPanel addNewFriendPanel = new JPanel();
		addNewFriendPanel.setLayout(new BoxLayout(addNewFriendPanel, BoxLayout.Y_AXIS));
		friend = new JTextField(15); // textfield to add a new friend
		friend.setMaximumSize(new Dimension(200, 30));
		addNewFriendPanel.add(friend);

		// Panel for the buttons: addNewFriend, removeFriend
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center buttons
		addNewFriendButton = createButtonWithIcon("addFriend.png");
		buttonPanel.add(addNewFriendButton);
		removeFriendButton = createButtonWithIcon("removeFriend.png");
		buttonPanel.add(removeFriendButton);
		addNewFriendPanel.add(buttonPanel);

		// Panel for adding a new post
		JPanel addNewPostPanel = new JPanel();
		addNewPostPanel.setLayout(new BoxLayout(addNewPostPanel, BoxLayout.Y_AXIS));
		addLabel("New post:", "Serif", 15, addNewPostPanel);
		newPost = new JTextField(15);
		newPost.setMaximumSize(new Dimension(200, 30));
		addNewPostPanel.add(newPost);

		// Panel for the post button
		JPanel newPostButtonPanel = new JPanel();
		newPostButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center the button
		addNewPostButton = new JButton("Post");
		addNewPostButton.addActionListener(new ButtonListener());
		newPostButtonPanel.add(addNewPostButton);
		addNewPostPanel.add(newPostButtonPanel);

		// Add subpanels to profilePanel
		profilePanel.add(imageNamePanel);
		profilePanel.add(showFriendsPanel);
		profilePanel.add(addNewFriendPanel);
		profilePanel.add(addNewPostPanel);

		infoPanel.add(profilePanel, BorderLayout.NORTH);
		addNewsfeedPanel(infoPanel); // TODO: modify this method as needed
	}

	/**
	 * Add a newsfeed panel for the user that is logged in. The newsfeed
	 * should include top most recent posts of this user and his/her friends (sorted
	 * by time, most recent first).
	 * You can write a similar method to general the newsfeed for the organization (that should list only organization's events)
	 *
	 * @param panel
	 */
	public void addNewsfeedPanel(JPanel panel) {
		// You may add additional parameter(s) if needed
		JPanel newsFeedPanel = new JPanel();
		newsFeedPanel.setLayout(new BoxLayout(newsFeedPanel, BoxLayout.Y_AXIS));
		newsFeedPanel.setBackground(Color.WHITE);

		// FILL IN CODE: change to get posts of the logged in user and their friends.
		String[] messagesFromAllFriends = {"post1 by Friend1", "post2 by Friend2", "post3 by Friend 3"};
		for (String message : messagesFromAllFriends) {
			// FILL IN CODE: Find a friend's profile
			// Create an image panel that will contain friend's image and message
			JPanel imageAndPostPanel = new JPanel();
			imageAndPostPanel.setLayout(new BorderLayout());
			imageAndPostPanel.setBackground(Color.WHITE);
			String imagefile = "no_image.png";// TODO: get the correct image of a friend
			addImage(imagefile, imageAndPostPanel);
			addLabel(message, "Serif", 15, imageAndPostPanel);
			newsFeedPanel.add(imageAndPostPanel);
			panel.add(newsFeedPanel, BorderLayout.CENTER);
		}
	}
}
