package database;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

// @@author A0130503B Zhongwei
/**
 * Junit testing of FileUtils
 * 
 * @author Zhongwei
 *
 */
public class FileUtilsTest {

	@Test
	public void testFileNameValidFileNames() throws IOException {
		assertTrue(FileUtils.isFilenameValid("valid.txt"));
		assertTrue(FileUtils.isFilenameValid("valid valid.txt"));
		assertTrue(FileUtils.isFilenameValid("va|id.t*t"));
		assertFalse(FileUtils.isFilenameValid("invalid . invalid"));
	}

	@Test
	public void testFileNameValidPathNames() {
		assertTrue(FileUtils.isFilenameValid("/Users/zhongwei-z/Desktop"));
		assertTrue(FileUtils.isFilenameValid("/"));
		assertTrue(FileUtils.isFilenameValid("/usr/"));
		assertFalse(FileUtils.isFilenameValid("////////"));
	}

}
