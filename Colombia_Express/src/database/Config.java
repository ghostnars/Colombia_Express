package database;
public class Config {
	
	private String version = "0.1.0.sqlite";
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
	public String CreatePromocion(){
		String Createpromocion = "CREATE TABLE IF NOT EXISTS PROMOCION(id_Afi TEXT, id_Promo TEXT, nombre_Promo TEXT, desc_Promo TEXT, imagen_Promo TEXT, fechainicio_Promo TEXT, fechafin_Promo TEXT, precio_Promo TEXT, estado_Promo TEXT)";
		return Createpromocion;
	}	
	
	/*============INSERTAR==============*/		

	public String InsertOferta(){
		String Insertoferta = "INSERT INTO OFERTA(id_Afi,id_Oferta,nombre_Oferta,desc_Oferta,imagen_Oferta,precio_Oferta)VALUES(";
		return Insertoferta;
	}
	public String InsertPromocion(){
		String Insertpromocion = "INSERT INTO PROMOCION(id_Afi,id_Promo,nombre_Promo,desc_Promo,imagen_Promo,fechainicio_Promo,fechafin_Promo,precio_Promo,estado_Promo)VALUES(";
		return Insertpromocion;
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
	
	public String SelectPromocion(){
		String Selectpromo = "SELECT * FROM PROMOCION WHERE id_Afi = ";
		return Selectpromo;
	}
	public String SelectPromocionDesc(){
		String Selectofertadesc = "SELECT id_Afi,id_Promo,nombre_Promo,desc_Promo,imagen_Promo,fechainicio_Promo,fechafin_Promo,precio_Promo,estado_Promo FROM PROMOCION WHERE id_Promo = ";
		return Selectofertadesc;
	}
}
