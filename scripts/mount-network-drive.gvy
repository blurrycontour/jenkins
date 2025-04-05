@Grab('com.hierynomus:smbj:0.14.0')
import com.hierynomus.mssmb2.*

def creds = com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials(
        com.cloudbees.plugins.credentials.common.StandardUsernamePasswordCredentials.class,
        jenkins.model.Jenkins.instance
    ).find { it.id == "shared-disk-credential" ? it : null }

def client = new com.hierynomus.smbj.SMBClient()
def connection = client.connect("host.docker.internal")
def ac = new com.hierynomus.smbj.auth.AuthenticationContext(creds.username, hudson.util.Secret.toString(creds.password).toCharArray(), "WORKGROUP")
def share = connection.authenticate(ac).connectShare("drive")

def file = share.openFile("info.txt", EnumSet.of(com.hierynomus.msdtyp.AccessMask.GENERIC_READ), null, SMB2ShareAccess.ALL, SMB2CreateDisposition.FILE_OPEN, null)
println file.inputStream.text

file?.close()
share?.close()
connection?.close()
