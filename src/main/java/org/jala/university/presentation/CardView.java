//package org.jala.university.presentation;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class CardView extends JFrame {
//    private final JLabel cardNumberTextField = new JLabel();
//    private final JLabel ccvCardTextField = new JLabel();
//    private final JLabel dueDateCardTextField = new JLabel();
//    private final JPanel cardPanel = new JPanel();
//    public CardView (){
//        initializeUI();
//        loadCardData();
//    }
//    private void initializeUI() {
//        setTitle("Credit Card");
//        setSize(600, 600);
//        setLocationRelativeTo(null);
//        setLayout(null);
//
//        cardPanel.setBounds(20,20,540,180);
//        cardPanel.setBackground(new Color(253, 181, 0));
//        cardPanel.setLayout(new BorderLayout());
//    }
//    public void loadCardData(){
//
//        if(searchUserCard()){
//            setUserCardData();
//        }else{
//            cardNumberTextField.setText(cardFormat("XXXXXXXXXXXXXXXX"));
//            ccvCardTextField.setText("ccv");
//            dueDateCardTextField.setText("00/00/0000");
//            JLabel message = new JLabel("No tiene tarjeta ? puede solicitar una");
//            message.setBounds(30, 430, 1000, 100); message.setFont(new Font("Arial", Font.PLAIN, 20));
//            ButtonSolitationForm buttonSolitationForm = new ButtonSolitationForm(); buttonSolitationForm.setFrame(this);
//            buttonSolitationForm.setBounds(30,520,250,28);
//            add(buttonSolitationForm);
//            add(message);
//        }
//
//        cardNumberTextField.setPreferredSize(new Dimension(300, 100)); cardNumberTextField.setFont(new Font("Arial", Font.PLAIN, 20));
//        ccvCardTextField.setPreferredSize(new Dimension(150, 100)); ccvCardTextField.setFont(new Font("Arial", Font.PLAIN, 18));
//        dueDateCardTextField.setPreferredSize(new Dimension(150, 100)); dueDateCardTextField.setFont(new Font("Arial", Font.PLAIN, 18));
//
//        cardPanel.add(cardNumberTextField, BorderLayout.CENTER);
//        cardPanel.add(ccvCardTextField, BorderLayout.LINE_END);
//        cardPanel.add(dueDateCardTextField, BorderLayout.SOUTH);
//
//        add(cardPanel);
//    }
//    public boolean searchUserCard(){
//        //buscar si el usuario tiene una tarjeta
//        return false;
//    }
//    private void setUserCardData() {
//        //Setear los datos de la tarjeta del usuario
//        //agregar botones
//    }
//    public String cardFormat(String numberCard){
//        StringBuilder formattedCard = new StringBuilder();
//        for(int i = 0 ; i < 16; i++){
//            formattedCard.append(numberCard.charAt(i));
//            if((i+1) % 4 == 0 && i < 15){
//                formattedCard.append(" ");
//            }
//        }
//        return formattedCard.toString();
//    }
//}
