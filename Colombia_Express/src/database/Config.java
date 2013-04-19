package database;
public class Config {
	
	private String version = "0.0.8.sqlite";
	private String path;
	
	public String Path(){
		path = "file:///SDCard/Databases/ColombiaExpress/Colombia_db"+version;
		return path;		
	}
	
	
	/*==============CREAR===============*/

	public String CreateOferta(){
		String Createoferta = "CREATE TABLE IF NOT EXISTS OFERTA(id_Afi TEXT, id_Oferta TEXT, nombre_Oferta TEXT, desc_Oferta TEXT, imagen_Oferta TEXT, precio_Oferta TEXT)";
		return Createoferta;
	}		
	
	/*============INSERTAR==============*/		

	public String InsertOferta(){
		String Insertoferta = "INSERT INTO OFERTA(id_Afi,id_Oferta,nombre_Oferta,desc_Oferta,imagen_Oferta,precio_Oferta)VALUES(";
		return Insertoferta;
	}
	

	
	/*==========SELECCIONAR=============*/
	

	public String SelectOferta(){
		String Selectoferta = "SELECT * FROM OFERTA WHERE id_Afi = ";
		return Selectoferta;
	}
	public String SelectOfertaDesc(){
	String Selectofertadesc = "SELECT id_Afi,id_Oferta,nombre_Oferta,desc_Oferta,imagen_Oferta,precio_Oferta FROM OFERTA WHERE id_Oferta = ";
	return Selectofertadesc;
	}
}
