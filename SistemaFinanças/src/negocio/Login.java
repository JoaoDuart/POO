package negocio;

import dados.Usuario;

import java.util.HashMap;
import java.util.Map;

public class Login {
    private Map<String, Usuario> usuarios;

    public Login() {
        this.usuarios = new HashMap<>();
        Usuario usuario = new Usuario("Joao", null, null, null, null, "123", null);
        this.usuarios.put(usuario.getNome(),usuario);
    }

    public void adicionaUsuario(String nome, Usuario usuario) {
        usuarios.put(nome, usuario);
    }

    public void alteraUsuario(String nome, Usuario novoUsuario) {
        if (usuarios.containsKey(nome)) {
            usuarios.put(nome, novoUsuario);
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public void removeUsuario(String nome) {
        usuarios.remove(nome);
    }

    public Usuario getUsuario(String nome) {
        return usuarios.get(nome);
    }

    public boolean verificarCredenciais(String nomeUsuario, String senha) {
        if (usuarios.containsKey(nomeUsuario)) {
            Usuario usuario = usuarios.get(nomeUsuario);
            return usuario.getSenha().equals(senha);
        } else {
            System.out.println("Usuário não encontrado.");
            return false;
        }
    }
}