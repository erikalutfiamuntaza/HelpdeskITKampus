package model;

public class Admin extends User {

    public void tampilRole() {
        System.out.println("Saya Admin");
    }

    @Override
    public void aksesDashboard(){System.out.println("Admin membuka dashboard admin");}

}