package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Continent;
import model.Country;
import view.CreateMap;

public class SaveCreatedMap {

	BufferedWriter bw;

	public SaveCreatedMap() {
		File f1 = new File("Resources/UserMap.map");
		try {
			if (f1.createNewFile())
				System.out.println("File created");
			else
				System.out.println("File already exists");
			FileOutputStream fos = new FileOutputStream(f1);
			bw = new BufferedWriter(new OutputStreamWriter(fos));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Init();

	}

	public void WriteIntoFile(String s) {
		try {

			bw.write(s);
			bw.newLine();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Init() {
		try {
			WriteIntoFile("[Map]");
			WriteIntoFile("Image=UserMap.bmp");
			WriteIntoFile("");
			WriteIntoFile("wrap=yes");
			WriteIntoFile("scroll=none");
			WriteIntoFile("warn=yes");
			bw.newLine();
			WriteIntoFile("[Continents]");
			for (Continent in : CreateMap.ContinentsObjectList) {
				WriteIntoFile(in.getName() + "=" + in.getControlValue());
			}
			bw.newLine();
			WriteIntoFile("[Territories]");

			Random rand1 = new Random();
			Random rand2 = new Random();

			int i = 1;
			for (Continent cc : CreateMap.ContinentsObjectList) {
				for (Country cn : cc.getCountries()) {
					int value1 = rand1.nextInt(255);
					int value2 = rand2.nextInt(255);
					bw.write(i + "," + cn.getName() + "," + value1 + "," + value2 + "," + cc.getName());

					for (Country cin : cn.getNeighbors()) {
						bw.write("," + cn.getName());
					}
					bw.newLine();
					i++;
				}
				bw.newLine();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
