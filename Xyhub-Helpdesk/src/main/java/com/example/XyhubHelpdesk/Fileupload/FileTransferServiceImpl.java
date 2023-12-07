//package com.example.XyhubHelpdesk.Fileupload;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//import org.apache.commons.compress.utils.IOUtils;
////import org.apache.xmlbeans.impl.common.IOUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.jcraft.jsch.Channel;
//import com.jcraft.jsch.ChannelSftp;
//import com.jcraft.jsch.JSch;
//import com.jcraft.jsch.JSchException;
//import com.jcraft.jsch.Session;
//import com.jcraft.jsch.SftpException;
//
//@Service
//public class FileTransferServiceImpl implements FileTransferService {
//
//	private Logger logger = LoggerFactory.getLogger(FileTransferServiceImpl.class);
//
//	@Value("${sftp.host}")
//	private String host;
//
//	@Value("${sftp.port}")
//	private Integer port;
//
//	@Value("${sftp.username}")
//	private String username;
//
//	@Value("${sftp.password}")
//	private String password;
//
//	@Value("${sftp.sessionTimeout}")
//	private Integer sessionTimeout;
//
//	@Value("${sftp.channelTimeout}")
//	private Integer channelTimeout;
//
//	@Value("${isKeyAuthRequired}")
//	private boolean isKeyAuthRequired;
//
//	@Value("${isPasswordRequired}")
//	private boolean isPasswordRequired;
//
//	String SFTPKEY = "/home/ubuntu/private-key/Ticketing-tool.ppk";
//
////	String SFTPKEY = "Ticketing-tool.ppk";
//	String SFTPWORKINGDIRAADMIN = "/home/ubuntu/tomcat-be/webapps";
//
//	@Override
//	public boolean uploadFile(MultipartFile file, String path, String fileName) {
//		ChannelSftp channelSftp = createChannelSftp();
//		String SFTPWORKINGDIR = SFTPWORKINGDIRAADMIN + "" + path;
//		try {
//			channelSftp.cd(SFTPWORKINGDIRAADMIN);
//
//			String[] folders = path.split("/");
//			for (String folder : folders) {
//				if (folder.length() > 0) {
//					try {
//						channelSftp.cd(folder);
//					} catch (SftpException e) {
//						channelSftp.mkdir(folder);
//						channelSftp.cd(folder);
//					}
//				}
//			}
//			channelSftp.put(file.getInputStream(), fileName);
//
//			return true;
//		} catch (SftpException ex) {
//			logger.error("Error upload file", ex);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			disconnectChannelSftp(channelSftp);
//		}
//
//		return false;
//	}
//
//	@Override
//	public boolean uploadTimeSheetFile(com.itextpdf.text.pdf.codec.Base64.InputStream stream, String path,
//			String fileName) {
//		ChannelSftp channelSftp = createChannelSftp();
//		String SFTPWORKINGDIR = SFTPWORKINGDIRAADMIN + "" + path;
//		try {
//
//			channelSftp.cd(SFTPWORKINGDIRAADMIN);
//
//			String[] folders = path.split("/");
//			for (String folder : folders) {
//				if (folder.length() > 0) {
//					try {
//						channelSftp.cd(folder);
//					} catch (SftpException e) {
//						channelSftp.mkdir(folder);
//						channelSftp.cd(folder);
//					}
//				}
//			}
//			channelSftp.put(stream, fileName);
//
//			return true;
//		} catch (SftpException ex) {
//			logger.error("Error upload file", ex);
//		} finally {
//			disconnectChannelSftp(channelSftp);
//		}
//
//		return false;
//	}
//
////	@Override
////	public boolean downloadFile(String localFilePath, remoteFilePath) {
////		ChannelSftp channelSftp = createChannelSftp();
////		OutputStream outputStream = null;
////		try {
////			File file1 = new File(localFilePath);
////			outputStream = new FileOutputStream(file1);
////			channelSftp.get(remoteFilePath, outputStream);
////			file1.createNewFile();
////			return true;
////		} catch (SftpException | IOException ex) {
////			logger.error("Error download file", ex);
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} finally {
////			disconnectChannelSftp(channelSftp);
////		}
////
////		return false;
////	}
//
//	private ChannelSftp createChannelSftp() {
//		try {
//			JSch jSch = new JSch();
//
//			if (isKeyAuthRequired) {
//				if (SFTPKEY != null && !SFTPKEY.isEmpty()) {
//					jSch.addIdentity(SFTPKEY);
//				}
//			}
//
//			Session session = jSch.getSession(username, host, port);
//			session.setConfig("StrictHostKeyChecking", "no");
//
//			if (isPasswordRequired) {
//				session.setPassword(password);
//			}
//			session.connect(sessionTimeout);
//			Channel channel = session.openChannel("sftp");
//			channel.connect(channelTimeout);
//			return (ChannelSftp) channel;
//		} catch (JSchException ex) {
//			logger.error("Create ChannelSftp error", ex);
//		}
//
//		return null;
//	}
//
//	private void disconnectChannelSftp(ChannelSftp channelSftp) {
//		try {
//			if (channelSftp == null)
//				return;
//
//			if (channelSftp.isConnected())
//				channelSftp.disconnect();
//
//			if (channelSftp.getSession() != null)
//				channelSftp.getSession().disconnect();
//
//		} catch (Exception ex) {
//			logger.error("SFTP disconnect error", ex);
//		}
//	}
//
//	@Override
//	public boolean deleteFile(String fileName, String path) {
//		ChannelSftp channelSftp = createChannelSftp();
//		String SFTPWORKINGDIR = SFTPWORKINGDIRAADMIN + "" + path;
//		try {
//			
//			channelSftp.cd(SFTPWORKINGDIRAADMIN);
//
//			String[] folders = path.split("/");
//			for (String folder : folders) {
//				if (folder.length() > 0) {
//					try {
//						channelSftp.cd(folder);
//					} catch (SftpException e) {
//						channelSftp.mkdir(folder);
//						channelSftp.cd(folder);
//					}
//				}
//			}
//		 // Change Directory on SFTP Server
//			channelSftp.rm(fileName); // This method removes the file from remote server
//
//			return true;
//		} catch (SftpException ex) {
//			logger.error("Error upload file", ex);
//		} finally {
//			disconnectChannelSftp(channelSftp);
//		}
//
//		return false;
//	}
//
//	@Override
//	public byte[] downloadFile(String localFilePath, String path, String filename) {
//		// TODO Auto-generated method stub
//		ChannelSftp channelSftp = createChannelSftp();
//		String SFTPWORKINGDIR = SFTPWORKINGDIRAADMIN + "" + path;
//		try {
//
//			channelSftp.cd(SFTPWORKINGDIRAADMIN);
//
//			String[] folders = path.split("/");
//			for (String folder : folders) {
//				if (folder.length() > 0) {
//					try {
//						channelSftp.cd(folder);
//					} catch (SftpException e) {
//						channelSftp.mkdir(folder);
//						channelSftp.cd(folder);
//					}
//				}
//			}
//			InputStream inputStream = (InputStream) channelSftp.get(filename);
//			byte[] filebytes = IOUtils.toByteArray(inputStream);
//			return filebytes;
//		} catch (SftpException | IOException ex) {
//			logger.error("Error download file", ex);
//		} finally {
//			disconnectChannelSftp(channelSftp);
//		}
//
//		return null;
//	}
//
//}
