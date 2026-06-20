package model;

public class Mahasiswa extends User {

    public void tampilRole() {
        System.out.println("Saya Mahasiswa");
    }

    @Override
    public void aksesDashboard(){System.out.println("Mahasiswa membuka dashboard mahasiswa");}

}