package net.originmobi.pdv.utilitarios;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Repository;

@Repository
public class DataAtual {

	public DataAtual() {
	}

	public String DataAtualIncrementa(int diasAMais) {
		String dataAlterada = "";

		try {
			Calendar dataAtual = Calendar.getInstance();
			dataAtual.setTime(new Date());
			dataAtual.add(Calendar.DATE, diasAMais);

			SimpleDateFormat formatar = new SimpleDateFormat("yyyy-MM-dd");
			dataAlterada = formatar.format(dataAtual.getTime());

		} catch (Exception e) {
			e.getStackTrace();
		}

		return dataAlterada;
	}

	public Timestamp dataAtualTimeStamp() {
		Timestamp dataAtual = new Timestamp(System.currentTimeMillis());
		return dataAtual;
	}

}
