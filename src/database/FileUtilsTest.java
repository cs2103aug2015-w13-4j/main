package database;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.assertTrue;

// @@author A0130503B
public class FileUtilsTest {

	@Test
	public void testFileNameValidFileNames() throws IOException {
		assertTrue(FileUtils.isFilenameValid("valid.txt"));
		assertTrue(FileUtils.isFilenameValid("valid valid.txt"));
		assertTrue(FileUtils.isFilenameValid("inva|id.t*t"));
		File f = new File("inva|id.t*t");
		PrintWriter w = new PrintWriter(new FileWriter(f));
		w.write(52);
	}
	
	@Test
	public void testFileNameValidPathNames() {
		assertTrue(FileUtils.isFilenameValid("/Users/zhongwei-z/Desktop"));
		assertTrue(FileUtils.isFilenameValid("/"));
		assertTrue(FileUtils.isFilenameValid("/usr/"));
	}

}
