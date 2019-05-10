# Note, we can probably remove the lzma option as it has be replaced with xz,
# and I don't think the kernel supports it any more.
SUMMARY = "Tools for manipulating SquashFS filesystems"
SECTION = "base"
LICENSE = "GPL-2"
LIC_FILES_CHKSUM = "file://../COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PV = "4.3+gitr${SRCPV}"
SRCREV = "3197524cb62f705ffd4da9b63b6c22ff7cb3dc77"
SRC_URI = "git://github.com/plougher/squashfs-tools.git;protocol=https \
           file://0001-mksquashfs.c-get-inline-functions-work-with-both-gnu.patch;striplevel=2 \
           file://0002-mksquashfs-unsquashfs-fix-compilation-with-glibc-2.2.patch;striplevel=2 \
           file://0003-squashfs-tools-patch-for-CVE-2015-4645-6.patch;striplevel=2 \
"

UPSTREAM_CHECK_COMMITS = "1"

S = "${WORKDIR}/git/squashfs-tools"

PACKAGECONFIG ??= "gzip xz lzo lz4 lzma xattr"
PACKAGECONFIG[gzip] = "GZIP_SUPPORT=1,GZIP_SUPPORT=0,zlib"
PACKAGECONFIG[xz] = "XZ_SUPPORT=1,XZ_SUPPORT=0,xz"
PACKAGECONFIG[lzo] = "LZO_SUPPORT=1,LZO_SUPPORT=0,lzo"
PACKAGECONFIG[lz4] = "LZ4_SUPPORT=1,LZ4_SUPPORT=0,lz4"
PACKAGECONFIG[lzma] = "LZMA_XZ_SUPPORT=1,LZMA_XZ_SUPPORT=0,xz"
PACKAGECONFIG[xattr] = "XATTR_SUPPORT=1,XATTR_SUPPORT=0,attr"

EXTRA_OEMAKE = "${PACKAGECONFIG_CONFARGS}"

do_compile() {
	oe_runmake mksquashfs unsquashfs
}

do_install () {
	install -d ${D}${sbindir}
	install -m 0755 mksquashfs ${D}${sbindir}/
	install -m 0755 unsquashfs ${D}${sbindir}/
}

BBCLASSEXTEND = "native nativesdk"

CVE_PRODUCT = "squashfs"
