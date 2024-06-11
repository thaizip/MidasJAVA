package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cupom;
import singleton.Conexao;

public class CupomDao {
	private Connection conexao;

	public CupomDao() {
		conexao = Conexao.getInstancia().getConexao();
	}

	public String salvar(Cupom cupom) {
		String sql = "";
		if (cupom.getCodigo()> 0 ) {
			sql = "UPDATE cupom SET titulo = ?, descricao = ?, ponto = ?, dtvencimento = ? WHERE codigo = ? ";
		}else {
			sql = " INSERT INTO cupom (titulo, descricao, ponto, dtvencimento) "+
					" VALUES (?, ?, ?, ?) ";
		}
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, cupom.getTitulo());
			ps.setString(2, cupom.getDescricao());
			ps.setString(3, cupom.getPonto());
			ps.setString(4, cupom.getDtvencimento());
			if(cupom.getCodigo()> 0){
				ps.setInt(5, cupom.getCodigo());

			}
			ps.executeUpdate();
			ps.close();
			return "Cupom gravado!";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Houve um erro! Tente novamente!";
		}
	}

	public List<Cupom> listarTodos() {
		List<Cupom> listaCupons = new ArrayList<>();
		String sql = " SELECT * FROM cupom ";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cupom cupom = new Cupom();
				cupom.setCodigo(rs.getInt("codigo"));
				cupom.setTitulo(rs.getString("titulo"));
				cupom.setDescricao(rs.getString("descricao"));
				cupom.setPonto(rs.getString("ponto"));
				cupom.setDtvencimento(rs.getString("dtvencimento"));
				listaCupons.add(cupom);
			}
			ps.close();
			return listaCupons;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Cupom getPorCodigo(int codigo) {
		Cupom cupom = new Cupom();
		String sql = "SELECT * FROM cupom WHERE codigo = ?";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				cupom.setCodigo(rs.getInt("codigo"));
				cupom.setTitulo(rs.getString("titulo"));
				cupom.setDescricao(rs.getString("descricao"));
				cupom.setPonto(rs.getString("ponto"));
				cupom.setDtvencimento(rs.getString("dtvencimento"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cupom;
	}
	public String excluir(int codigo) {
		String sql = "DELETE FROM cupom WHERE codigo = ?";
		try (PreparedStatement ps = this.conexao.prepareStatement(sql)) {
			ps.setInt(1, codigo);
			ps.execute();
			ps.close();
			return "Cupom exclu�do com sucesso.";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Erro ao excluir o cupom: " + e.getMessage();
		}
	}
}
