package dao;

import model.Recompensa;
import singleton.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecompensaDao {
	private Connection conexao;

	public RecompensaDao() {
		conexao = Conexao.getInstancia().getConexao();
	}

	public String salvar(Recompensa recompensa) {
		String sql = "";
		if (recompensa.getCodigo()> 0 ) {
			sql = "UPDATE recompensa SET titulo = ?, descricao = ?, ponto = ?, dtvencimento = ? WHERE codigo = ? ";
		}else {
			sql = " INSERT INTO recompensa (titulo, descricao, ponto, dtvencimento) "+
					" VALUES (?, ?, ?, ?) ";
		}
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, recompensa.getTitulo());
			ps.setString(2, recompensa.getDescricao());
			ps.setString(3, recompensa.getPonto());
			ps.setString(4, recompensa.getDtvencimento());
			if(recompensa.getCodigo()> 0){
				ps.setInt(5, recompensa.getCodigo());

			}
			ps.executeUpdate();
			ps.close();
			return "Recompensa gravada!";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Houve um erro! Tente novamente!";
		}
	}

	public List<Recompensa> listarTodos() {
		List<Recompensa> listaRecompensas = new ArrayList<>();
		String sql = " SELECT * FROM recompensa ";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Recompensa recompensa = new Recompensa();
				recompensa.setCodigo(rs.getInt("codigo"));
				recompensa.setTitulo(rs.getString("titulo"));
				recompensa.setDescricao(rs.getString("descricao"));
				recompensa.setPonto(rs.getString("ponto"));
				recompensa.setDtvencimento(rs.getString("dtvencimento"));
				listaRecompensas.add(recompensa);
			}
			ps.close();
			return listaRecompensas;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Recompensa getPorCodigo(int codigo) {
		Recompensa recompensa = new Recompensa();
		String sql = "SELECT * FROM recompensa WHERE codigo = ?";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				recompensa.setCodigo(rs.getInt("codigo"));
				recompensa.setTitulo(rs.getString("titulo"));
				recompensa.setDescricao(rs.getString("descricao"));
				recompensa.setPonto(rs.getString("ponto"));
				recompensa.setDtvencimento(rs.getString("dtvencimento"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recompensa;
	}
	public String excluir(int codigo) {
		String sql = "DELETE FROM recompensa WHERE codigo = ?";
		try (PreparedStatement ps = this.conexao.prepareStatement(sql)) {
			ps.setInt(1, codigo);
			ps.execute();
			ps.close();
			return "Recompensa excluido com sucesso.";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Erro ao excluir a Recompensa: " + e.getMessage();
		}
	}
}
