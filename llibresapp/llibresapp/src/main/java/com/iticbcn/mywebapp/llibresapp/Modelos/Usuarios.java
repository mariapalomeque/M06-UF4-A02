package com.iticbcn.mywebapp.llibresapp.Modelos;

public class Usuarios {
    
    private String usuario;
    private String contra;


    public Usuarios() {}

    public Usuarios(String contra, String usuario) {
        this.contra = contra;
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }


}
