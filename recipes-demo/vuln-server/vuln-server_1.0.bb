SUMMARY = "Vulnerable Python Web Server for AppArmor Demo"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://vuln-server.py \
           file://usr.bin.vuln-server"

S = "${UNPACKDIR}"

do_install() {
    # Install the vulnerable script
    install -d ${D}${bindir}
    install -m 0755 ${S}/vuln-server.py ${D}${bindir}/vuln-server

    # Install the AppArmor profile
    install -d ${D}${sysconfdir}/apparmor.d
    install -m 0644 ${S}/usr.bin.vuln-server ${D}${sysconfdir}/apparmor.d/
}

RDEPENDS:${PN} += "python3-core python3-netserver"
