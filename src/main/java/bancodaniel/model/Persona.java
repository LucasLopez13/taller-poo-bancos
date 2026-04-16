package bancodaniel.model;

import bancodaniel.enums.Rol;

public class Persona {
    private String nombre;
    private String direccion;
    private String correo;
    private Cuenta cuenta;
    private String password;
    private Rol rol;


    private Persona(Builder builder) {
        this.nombre = builder.nombre;
        this.direccion = builder.direccion;
        this.correo = builder.correo;
        this.cuenta = builder.cuenta;
        this.password = builder.password;
        this.rol = builder.rol;
    }

    public static class Builder {
        private String nombre;
        private String direccion;
        private String correo;
        private Cuenta cuenta;
        private String password;
        private Rol rol = Rol.USUARIO;

        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setDireccion(String direccion) {
            this.direccion = direccion;
            return this;
        }

        public Builder setCorreo(String correo) {
            this.correo = correo;
            return this;
        }

        public Builder setCuenta(Cuenta cuenta) {
            this.cuenta = cuenta;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setRol(Rol rol) {
            this.rol = rol;
            return this;
        }

        public Persona build() {
            return new Persona(this);
        }
    }

    public void mostrarDatosPersona() {
        System.out.println("nombre: " + this.nombre);
        System.out.println("direccion: " + this.direccion);
        System.out.println("tipoCuenta: " + this.cuenta.getTipo());
        System.out.println("correo: " + this.correo);
        System.out.println("password: " + this.getPassword());
        System.out.println("rol: " + this.getRol());
        System.out.println("saldo: " + this.cuenta.getSaldo());
    }

    public String getNombre() {
        return this.nombre;
    }

    public Cuenta getCuenta() {
        return this.cuenta;
    }

    public String getCorreo() {
        return this.correo;
    }

    public String getPassword() {
        return this.password;
    }

    public Rol getRol() {
        return this.rol;
    }

    public void setRol(Rol rol){
        this.rol = rol;
    }
}
