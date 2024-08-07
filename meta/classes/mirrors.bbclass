MIRRORS += "\
${DEBIAN_MIRROR}	http://snapshot.debian.org/archive/debian/20180310T215105Z/pool \n \
${DEBIAN_MIRROR}	http://snapshot.debian.org/archive/debian-archive/20120328T092752Z/debian/pool \n \
${DEBIAN_MIRROR}	http://snapshot.debian.org/archive/debian-archive/20110127T084257Z/debian/pool \n \
${DEBIAN_MIRROR}	http://snapshot.debian.org/archive/debian-archive/20090802T004153Z/debian/pool \n \
${DEBIAN_MIRROR}	http://ftp.de.debian.org/debian/pool \n \
${DEBIAN_MIRROR}	http://ftp.au.debian.org/debian/pool \n \
${DEBIAN_MIRROR}	http://ftp.cl.debian.org/debian/pool \n \
${DEBIAN_MIRROR}	http://ftp.hr.debian.org/debian/pool \n \
${DEBIAN_MIRROR}	http://ftp.fi.debian.org/debian/pool \n \
${DEBIAN_MIRROR}	http://ftp.hk.debian.org/debian/pool \n \
${DEBIAN_MIRROR}	http://ftp.hu.debian.org/debian/pool \n \
${DEBIAN_MIRROR}	http://ftp.ie.debian.org/debian/pool \n \
${DEBIAN_MIRROR}	http://ftp.it.debian.org/debian/pool \n \
${DEBIAN_MIRROR}	http://ftp.jp.debian.org/debian/pool \n \
${DEBIAN_MIRROR}	http://ftp.no.debian.org/debian/pool \n \
${DEBIAN_MIRROR}	http://ftp.pl.debian.org/debian/pool \n \
${DEBIAN_MIRROR}	http://ftp.ro.debian.org/debian/pool \n \
${DEBIAN_MIRROR}	http://ftp.si.debian.org/debian/pool \n \
${DEBIAN_MIRROR}	http://ftp.es.debian.org/debian/pool \n \
${DEBIAN_MIRROR}	http://ftp.se.debian.org/debian/pool \n \
${DEBIAN_MIRROR}	http://ftp.tr.debian.org/debian/pool \n \
${GNU_MIRROR}	https://mirrors.kernel.org/gnu \n \
${KERNELORG_MIRROR}	http://www.kernel.org/pub \n \
${GNUPG_MIRROR}	ftp://ftp.gnupg.org/gcrypt \n \
${GNUPG_MIRROR}	ftp://ftp.franken.de/pub/crypt/mirror/ftp.gnupg.org/gcrypt \n \
${GNUPG_MIRROR}	ftp://mirrors.dotsrc.org/gcrypt \n \
ftp://dante.ctan.org/tex-archive ftp://ftp.fu-berlin.de/tex/CTAN \n \
ftp://dante.ctan.org/tex-archive http://sunsite.sut.ac.jp/pub/archives/ctan/ \n \
ftp://dante.ctan.org/tex-archive http://ctan.unsw.edu.au/ \n \
ftp://ftp.gnutls.org/gcrypt/gnutls ${GNUPG_MIRROR}/gnutls \n \
http://ftp.info-zip.org/pub/infozip/src/ ftp://sunsite.icm.edu.pl/pub/unix/archiving/info-zip/src/ \n \
http://www.mirrorservice.org/sites/lsof.itap.purdue.edu/pub/tools/unix/lsof/ http://www.mirrorservice.org/sites/lsof.itap.purdue.edu/pub/tools/unix/lsof/OLD/ \n \
${APACHE_MIRROR}  http://www.us.apache.org/dist \n \
${APACHE_MIRROR}  http://archive.apache.org/dist \n \
http://downloads.sourceforge.net/watchdog/ http://fossies.org/linux/misc/ \n \
${SAVANNAH_GNU_MIRROR} http://download-mirror.savannah.gnu.org/releases \n \
${SAVANNAH_NONGNU_MIRROR} http://download-mirror.savannah.nongnu.org/releases \n \
ftp://sourceware.org/pub http://mirrors.kernel.org/sourceware \n \
ftp://sourceware.org/pub http://gd.tuwien.ac.at/gnu/sourceware \n \
ftp://sourceware.org/pub http://ftp.gwdg.de/pub/linux/sources.redhat.com/sourceware \n \
${CPAN_MIRROR}  http://cpan.metacpan.org/ \n \
${CPAN_MIRROR}  http://search.cpan.org/CPAN/ \n \
https?$://downloads.yoctoproject.org/releases/uninative/ https://mirrors.kernel.org/yocto/uninative/ \n \
https?$://downloads.yoctoproject.org/mirror/sources/ https://mirrors.kernel.org/yocto-sources/ \n \
"

# Use MIRRORS to provide git repo fallbacks using the https protocol, for cases
# where git native protocol fetches may fail due to local firewall rules, etc.

MIRRORS += "\
git://salsa.debian.org/.*     git://salsa.debian.org/PATH;protocol=https \n \
git://git.gnome.org/.*        git://gitlab.gnome.org/GNOME/PATH;protocol=https \n \
git://git.savannah.gnu.org/.* git://git.savannah.gnu.org/git/PATH;protocol=https \n \
git://git.yoctoproject.org/.* git://git.yoctoproject.org/git/PATH;protocol=https \n \
git://.*/.*                   git://HOST/PATH;protocol=https \n \
"
