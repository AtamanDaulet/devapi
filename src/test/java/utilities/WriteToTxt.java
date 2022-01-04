package utilities;

import pojos.medunna.Registrant;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToTxt {

	public static void saveRegistrantData(String fileName, Registrant registrant){
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName, true);
			BufferedWriter writer = new BufferedWriter(fileWriter);

			writer.append(registrant.getFirstName()+", "+
							registrant.getLastName()+", "+
							registrant.getLogin()+", "+
							registrant.getPassword()+", "+
							registrant.getSsn()+", "+
							registrant.getEmail() +
							",\n");

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}



	}


}
