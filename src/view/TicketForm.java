package view;

import javax.swing.*;
import controller.TicketController;


public class TicketForm extends JFrame {


    JTextField txtJudul = new JTextField();

    JComboBox<String> cbKategori = new JComboBox<>();

    JTextArea txtDeskripsi = new JTextArea();


    JButton btnSubmit = new JButton("Submit");

    JButton btnKembali = new JButton("Kembali");



    public TicketForm() {


        setTitle("Buat Tiket");

        setSize(450,450);

        setLayout(null);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);



        JLabel lblJudul = new JLabel("Judul Keluhan");

        lblJudul.setBounds(30,20,120,25);

        add(lblJudul);



        txtJudul.setBounds(30,45,350,25);

        add(txtJudul);




        JLabel lblKategori = new JLabel("Kategori");

        lblKategori.setBounds(30,80,120,25);

        add(lblKategori);



        cbKategori.addItem("Jaringan");

        cbKategori.addItem("Hardware");

        cbKategori.addItem("Software");

        cbKategori.addItem("Lainnya");

        cbKategori.setBounds(30,105,350,25);

        add(cbKategori);




        JLabel lblDeskripsi = new JLabel("Deskripsi");

        lblDeskripsi.setBounds(30,140,120,25);

        add(lblDeskripsi);



        JScrollPane scroll = new JScrollPane(txtDeskripsi);

        scroll.setBounds(30,165,350,100);

        add(scroll);




        btnSubmit.setBounds(70,300,120,35);

        add(btnSubmit);



        btnKembali.setBounds(220,300,120,35);

        add(btnKembali);




        btnSubmit.addActionListener(e -> {



            String judul =
                    txtJudul.getText();



            String kategori =
                    cbKategori.getSelectedItem().toString();



            String deskripsi =
                    txtDeskripsi.getText();




            if(judul.isEmpty() || deskripsi.isEmpty()){



                JOptionPane.showMessageDialog(
                        this,
                        "Semua data harus diisi!"
                );



            }else{



                TicketController tc =
                        new TicketController();



                boolean hasil =
                        tc.createTicket(
                                judul,
                                kategori,
                                deskripsi,
                                2
                        );




                if(hasil){


                    JOptionPane.showMessageDialog(
                            this,
                            "Tiket berhasil dibuat!"
                    );


                    txtJudul.setText("");

                    txtDeskripsi.setText("");

                    cbKategori.setSelectedIndex(0);



                }else{


                    JOptionPane.showMessageDialog(
                            this,
                            "Gagal membuat tiket!"
                    );


                }

            }




        });





        btnKembali.addActionListener(e -> {


            dispose();

            new UserDashboard();


        });



        setVisible(true);

    }




    public static void main(String[] args){

        new TicketForm();

    }

}