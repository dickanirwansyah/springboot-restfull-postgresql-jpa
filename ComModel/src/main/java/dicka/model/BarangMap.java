package dicka.model;

public class BarangMap {

    private int idbarang;

    private String nama;

    private int quantity;

    private Jenis jenis;

    private int price;

    public BarangMap(){

    }

    public BarangMap(String nama, int quantity, Jenis jenis, int price){
        super();
        this.nama = nama;
        this.quantity = quantity;
        this.jenis = jenis;
        this.price = price;
    }

    public int getIdbarang(){
        return idbarang;
    }

    public void setIdbarang(int idbarang){
        this.idbarang = idbarang;
    }

    public String getNama(){
        return nama;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public Jenis getJenis() {
        return jenis;
    }

    public void setJenis(Jenis jenis) {
        this.jenis = jenis;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
