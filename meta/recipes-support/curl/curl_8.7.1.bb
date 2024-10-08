SUMMARY = "Command line tool and library for client-side URL transfers"
DESCRIPTION = "It uses URL syntax to transfer data to and from servers. \
curl is a widely used because of its ability to be flexible and complete \
complex tasks. For example, you can use curl for things like user authentication, \
HTTP post, SSL connections, proxy support, FTP uploads, and more!"
HOMEPAGE = "https://curl.se/"
BUGTRACKER = "https://github.com/curl/curl/issues"
SECTION = "console/network"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=eed2e5088e1ac619c9a1c747da291d75"

SRC_URI = " \
    https://curl.se/download/${BP}.tar.xz \
    file://721941aadf4adf4f6aeb3f4c0ab489bb89610c36.patch \
    file://CVE-2024-6197.patch \
    file://CVE-2024-7264-1.patch \
    file://CVE-2024-7264-2.patch \
"
SRC_URI[sha256sum] = "6fea2aac6a4610fbd0400afb0bcddbe7258a64c63f1f68e5855ebc0c659710cd"

# Curl has used many names over the years...
CVE_PRODUCT = "haxx:curl haxx:libcurl curl:curl curl:libcurl libcurl:libcurl daniel_stenberg:curl"
CVE_CHECK_WHITELIST = "CVE-2021-22922 CVE-2021-22923 CVE-2021-22926 CVE-2021-22945"

# As per link https://security-tracker.debian.org/tracker/CVE-2021-22897
# and https://ubuntu.com/security/CVE-2021-22897
# This CVE issue affects Windows only Hence whitelisting this CVE
CVE_CHECK_WHITELIST += "CVE-2021-22897"

# This CVE reports that apple had to upgrade curl because of other already reported CVEs
CVE_CHECK_WHITELIST += "CVE-2023-42915"

inherit autotools pkgconfig binconfig multilib_header

# Entropy source for random PACKAGECONFIG option
RANDOM ?= "/dev/urandom"

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'ipv6', d)} aws basic-auth bearer-auth digest-auth negotiate-auth libidn ssl proxy random threaded-resolver verbose zlib"
PACKAGECONFIG_class-native = "ipv6 ssl proxy random threaded-resolver verbose zlib"
PACKAGECONFIG_class-nativesdk = "ipv6 ssl proxy random threaded-resolver verbose zlib"

# 'ares' and 'threaded-resolver' are mutually exclusive
PACKAGECONFIG[ares] = "--enable-ares,--disable-ares,c-ares,,,threaded-resolver"
PACKAGECONFIG[aws] = "--enable-aws,--disable-aws"
PACKAGECONFIG[basic-auth] = "--enable-basic-auth,--disable-basic-auth"
PACKAGECONFIG[bearer-auth] = "--enable-bearer-auth,--disable-bearer-auth"
PACKAGECONFIG[brotli] = "--with-brotli,--without-brotli,brotli"
PACKAGECONFIG[builtinmanual] = "--enable-manual,--disable-manual"
# Don't use this in production
PACKAGECONFIG[debug] = "--enable-debug,--disable-debug"
PACKAGECONFIG[dict] = "--enable-dict,--disable-dict,"
PACKAGECONFIG[digest-auth] = "--enable-digest-auth,--disable-digest-auth"
PACKAGECONFIG[gnutls] = "--with-gnutls,--without-gnutls,gnutls"
PACKAGECONFIG[gopher] = "--enable-gopher,--disable-gopher,"
PACKAGECONFIG[imap] = "--enable-imap,--disable-imap,"
PACKAGECONFIG[ipv6] = "--enable-ipv6,--disable-ipv6,"
PACKAGECONFIG[kerberos-auth] = "--enable-kerberos-auth,--disable-kerberos-auth"
PACKAGECONFIG[krb5] = "--with-gssapi,--without-gssapi,krb5"
PACKAGECONFIG[ldap] = "--enable-ldap,--disable-ldap,"
PACKAGECONFIG[ldaps] = "--enable-ldaps,--disable-ldaps,"
PACKAGECONFIG[libgsasl] = "--with-libgsasl,--without-libgsasl,libgsasl"
PACKAGECONFIG[libidn] = "--with-libidn2,--without-libidn2,libidn2"
PACKAGECONFIG[libssh2] = "--with-libssh2,--without-libssh2,libssh2"
PACKAGECONFIG[mbedtls] = "--with-mbedtls=${STAGING_DIR_TARGET},--without-mbedtls,mbedtls"
PACKAGECONFIG[mqtt] = "--enable-mqtt,--disable-mqtt,"
PACKAGECONFIG[negotiate-auth] = "--enable-negotiate-auth,--disable-negotiate-auth"
PACKAGECONFIG[nghttp2] = "--with-nghttp2,--without-nghttp2,nghttp2"
PACKAGECONFIG[ssl] = "--with-openssl,--without-openssl,openssl"
PACKAGECONFIG[pop3] = "--enable-pop3,--disable-pop3,"
PACKAGECONFIG[proxy] = "--enable-proxy,--disable-proxy,"
PACKAGECONFIG[random] = "--with-random=${RANDOM},--without-random"
PACKAGECONFIG[rtmpdump] = "--with-librtmp,--without-librtmp,rtmpdump"
PACKAGECONFIG[rtsp] = "--enable-rtsp,--disable-rtsp,"
PACKAGECONFIG[smb] = "--enable-smb,--disable-smb,"
PACKAGECONFIG[smtp] = "--enable-smtp,--disable-smtp,"
PACKAGECONFIG[telnet] = "--enable-telnet,--disable-telnet,"
PACKAGECONFIG[tftp] = "--enable-tftp,--disable-tftp,"
PACKAGECONFIG[threaded-resolver] = "--enable-threaded-resolver,--disable-threaded-resolver,,,,ares"
PACKAGECONFIG[verbose] = "--enable-verbose,--disable-verbose"
PACKAGECONFIG[zlib] = "--with-zlib=${STAGING_LIBDIR}/../,--without-zlib,zlib"
PACKAGECONFIG[zstd] = "--with-zstd,--without-zstd,zstd"

EXTRA_OECONF = " \
    --disable-libcurl-option \
    --disable-ntlm-wb \
    --with-ca-bundle=${sysconfdir}/ssl/certs/ca-certificates.crt \
    --without-libpsl \
    --enable-optimize \
    ${@'--without-ssl' if (bb.utils.filter('PACKAGECONFIG', 'gnutls mbedtls openssl ssl', d) == '') else ''} \
"

do_install_append_class-target() {
	# cleanup buildpaths from curl-config
	sed -i \
	    -e 's,--sysroot=${STAGING_DIR_TARGET},,g' \
	    -e 's,--with-libtool-sysroot=${STAGING_DIR_TARGET},,g' \
	    -e 's|${DEBUG_PREFIX_MAP}||g' \
	    ${D}${bindir}/curl-config
}

PACKAGES =+ "lib${BPN}"

FILES_lib${BPN} = "${libdir}/lib*.so.*"
RRECOMMENDS_lib${BPN} += "ca-certificates"

FILES_${PN} += "${datadir}/zsh"

inherit multilib_script
MULTILIB_SCRIPTS = "${PN}-dev:${bindir}/curl-config"

BBCLASSEXTEND = "native nativesdk"
