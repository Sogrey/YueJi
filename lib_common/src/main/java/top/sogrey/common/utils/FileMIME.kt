package top.sogrey.common.utils

/**
 * File MIME
 */
class FileMIME {
    companion object {
        fun getFileMIME(paramString: String): String {
            var str = ""
            if (paramString.contains("."))
                str =
                    paramString.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[-1 + paramString.split(
                        "\\.".toRegex()
                    ).dropLastWhile { it.isEmpty() }.toTypedArray().size]
            return getMimeType(str)
        }

        private fun getMimeType(paramString: String): String {
            val localHashMap: HashMap<String, String> = HashMap()
            localHashMap.put("", "application/octet-stream")
            localHashMap.put("323", "text/h323")
            localHashMap.put("3gp", "video/3gpp")
            localHashMap.put("aab", "application/x-authoware-bin")
            localHashMap.put("aam", "application/x-authoware-map")
            localHashMap.put("aas", "application/x-authoware-seg")
            localHashMap.put("acx", "application/internet-property-stream")
            localHashMap.put("ai", "application/postscript")
            localHashMap.put("aif", "audio/x-aiff")
            localHashMap.put("aifc", "audio/x-aiff")
            localHashMap.put("aiff", "audio/x-aiff")
            localHashMap.put("als", "audio/X-Alpha5")
            localHashMap.put("amc", "application/x-mpeg")
            localHashMap.put("ani", "application/octet-stream")
            localHashMap.put("apk", "application/vnd.android.package-archive")
            localHashMap.put("asc", "text/plain")
            localHashMap.put("asd", "application/astound")
            localHashMap.put("asf", "video/x-ms-asf")
            localHashMap.put("asn", "application/astound")
            localHashMap.put("asp", "application/x-asap")
            localHashMap.put("asr", "video/x-ms-asf")
            localHashMap.put("asx", "video/x-ms-asf")
            localHashMap.put("au", "audio/basic")
            localHashMap.put("avb", "application/octet-stream")
            localHashMap.put("avi", "video/x-msvideo")
            localHashMap.put("awb", "audio/amr-wb")
            localHashMap.put("axs", "application/olescript")
            localHashMap.put("bas", "text/plain")
            localHashMap.put("bcpio", "application/x-bcpio")
            localHashMap.put("bin ", "application/octet-stream")
            localHashMap.put("bld", "application/bld")
            localHashMap.put("bld2", "application/bld2")
            localHashMap.put("bmp", "image/bmp")
            localHashMap.put("bpk", "application/octet-stream")
            localHashMap.put("bz2", "application/x-bzip2")
            localHashMap.put("c", "text/plain")
            localHashMap.put("cal", "image/x-cals")
            localHashMap.put("cat", "application/vnd.ms-pkiseccat")
            localHashMap.put("ccn", "application/x-cnc")
            localHashMap.put("cco", "application/x-cocoa")
            localHashMap.put("cdf", "application/x-cdf")
            localHashMap.put("cer", "application/x-x509-ca-cert")
            localHashMap.put("cgi", "magnus-internal/cgi")
            localHashMap.put("chat", "application/x-chat")
            localHashMap.put("class", "application/octet-stream")
            localHashMap.put("clp", "application/x-msclip")
            localHashMap.put("cmx", "image/x-cmx")
            localHashMap.put("co", "application/x-cult3d-object")
            localHashMap.put("cod", "image/cis-cod")
            localHashMap.put("conf", "text/plain")
            localHashMap.put("cpio", "application/x-cpio")
            localHashMap.put("cpp", "text/plain")
            localHashMap.put("cpt", "application/mac-compactpro")
            localHashMap.put("crd", "application/x-mscardfile")
            localHashMap.put("crl", "application/pkix-crl")
            localHashMap.put("crt", "application/x-x509-ca-cert")
            localHashMap.put("csh", "application/x-csh")
            localHashMap.put("csm", "chemical/x-csml")
            localHashMap.put("csml", "chemical/x-csml")
            localHashMap.put("css", "text/css")
            localHashMap.put("cur", "application/octet-stream")
            localHashMap.put("dcm", "x-lml/x-evm")
            localHashMap.put("dcr", "application/x-director")
            localHashMap.put("dcx", "image/x-dcx")
            localHashMap.put("der", "application/x-x509-ca-cert")
            localHashMap.put("dhtml", "text/html")
            localHashMap.put("dir", "application/x-director")
            localHashMap.put("dll", "application/x-msdownload")
            localHashMap.put("dmg", "application/octet-stream")
            localHashMap.put("dms", "application/octet-stream")
            localHashMap.put("doc", "application/msword")
            localHashMap.put(
                "docx",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
            )
            localHashMap.put("dot", "application/msword")
            localHashMap.put("dvi", "application/x-dvi")
            localHashMap.put("dwf", "drawing/x-dwf")
            localHashMap.put("dwg", "application/x-autocad")
            localHashMap.put("dxf", "application/x-autocad")
            localHashMap.put("dxr", "application/x-director")
            localHashMap.put("ebk", "application/x-expandedbook")
            localHashMap.put("emb", "chemical/x-embl-dl-nucleotide")
            localHashMap.put("embl", "chemical/x-embl-dl-nucleotide")
            localHashMap.put("eps", "application/postscript")
            localHashMap.put("epub", "application/epub+zip")
            localHashMap.put("eri", "image/x-eri")
            localHashMap.put("es", "audio/echospeech")
            localHashMap.put("esl", "audio/echospeech")
            localHashMap.put("etc", "application/x-earthtime")
            localHashMap.put("etx", "text/x-setext")
            localHashMap.put("evm", "x-lml/x-evm")
            localHashMap.put("evy", "application/envoy")
            localHashMap.put("exe", "application/octet-stream")
            localHashMap.put("fh4", "image/x-freehand")
            localHashMap.put("fh5", "image/x-freehand")
            localHashMap.put("fhc", "image/x-freehand")
            localHashMap.put("fif", "application/fractals")
            localHashMap.put("flr", "x-world/x-vrml")
            localHashMap.put("flv", "video/octet-stream")
            localHashMap.put("fm", "application/x-maker")
            localHashMap.put("fpx", "image/x-fpx")
            localHashMap.put("fvi", "video/isivideo")
            localHashMap.put("gau", "chemical/x-gaussian-input")
            localHashMap.put("gca", "application/x-gca-compressed")
            localHashMap.put("gdb", "x-lml/x-gdb")
            localHashMap.put("gif", "image/gif")
            localHashMap.put("gps", "application/x-gps")
            localHashMap.put("gtar", "application/x-gtar")
            localHashMap.put("gz", "application/x-gzip")
            localHashMap.put("h", "text/plain")
            localHashMap.put("hdf", "application/x-hdf")
            localHashMap.put("hdm", "text/x-hdml")
            localHashMap.put("hdml", "text/x-hdml")
            localHashMap.put("hlp", "application/winhlp")
            localHashMap.put("hqx", "application/mac-binhex40")
            localHashMap.put("hta", "application/hta")
            localHashMap.put("htc", "text/x-component")
            localHashMap.put("htm", "text/html")
            localHashMap.put("html", "text/html")
            localHashMap.put("hts", "text/html")
            localHashMap.put("htt", "text/webviewhtml")
            localHashMap.put("ice", "x-conference/x-cooltalk")
            localHashMap.put("ico", "image/x-icon")
            localHashMap.put("ief", "image/ief")
            localHashMap.put("ifm", "image/gif")
            localHashMap.put("ifs", "image/ifs")
            localHashMap.put("iii", "application/x-iphone")
            localHashMap.put("imy", "audio/melody")
            localHashMap.put("ins", "application/x-internet-signup")
            localHashMap.put("ips", "application/x-ipscript")
            localHashMap.put("ipx", "application/x-ipix")
            localHashMap.put("isp", "application/x-internet-signup")
            localHashMap.put("it", "audio/x-mod")
            localHashMap.put("itz", "audio/x-mod")
            localHashMap.put("ivr", "i-world/i-vrml")
            localHashMap.put("j2k", "image/j2k")
            localHashMap.put("jad", "text/vnd.sun.j2me.app-descriptor")
            localHashMap.put("jam", "application/x-jam")
            localHashMap.put("jar", "application/java-archive")
            localHashMap.put("java", "text/plain")
            localHashMap.put("jfif", "image/pipeg")
            localHashMap.put("jnlp", "application/x-java-jnlp-file")
            localHashMap.put("jpe", "image/jpeg")
            localHashMap.put("jpeg", "image/jpeg")
            localHashMap.put("jpg", "image/jpeg")
            localHashMap.put("jpz", "image/jpeg")
            localHashMap.put("js", "application/x-javascript")
            localHashMap.put("json", "application/json")
            localHashMap.put("jwc", "application/jwc")
            localHashMap.put("kjx", "application/x-kjx")
            localHashMap.put("lak", "x-lml/x-lak")
            localHashMap.put("latex", "application/x-latex")
            localHashMap.put("lcc", "application/fastman")
            localHashMap.put("lcl", "application/x-digitalloca")
            localHashMap.put("lcr", "application/x-digitalloca")
            localHashMap.put("lgh", "application/lgh")
            localHashMap.put("lha", "application/octet-stream")
            localHashMap.put("lml", "x-lml/x-lml")
            localHashMap.put("lmlpack", "x-lml/x-lmlpack")
            localHashMap.put("log", "text/plain")
            localHashMap.put("lsf", "video/x-la-asf")
            localHashMap.put("lsx", "video/x-la-asf")
            localHashMap.put("lzh", "application/octet-stream")
            localHashMap.put("m13", "application/x-msmediaview")
            localHashMap.put("m14", "application/x-msmediaview")
            localHashMap.put("m15", "audio/x-mod")
            localHashMap.put("m3u", "audio/x-mpegurl")
            localHashMap.put("m3u8", "application/x-mpegURL")
            localHashMap.put("m3url", "audio/x-mpegurl")
            localHashMap.put("m4a", "audio/mp4a-latm")
            localHashMap.put("m4b", "audio/mp4a-latm")
            localHashMap.put("m4p", "audio/mp4a-latm")
            localHashMap.put("m4u", "video/vnd.mpegurl")
            localHashMap.put("m4v", "video/x-m4v")
            localHashMap.put("ma1", "audio/ma1")
            localHashMap.put("ma2", "audio/ma2")
            localHashMap.put("ma3", "audio/ma3")
            localHashMap.put("ma5", "audio/ma5")
            localHashMap.put("man", "application/x-troff-man")
            localHashMap.put("map", "magnus-internal/imagemap")
            localHashMap.put("mbd", "application/mbedlet")
            localHashMap.put("mct", "application/x-mascot")
            localHashMap.put("mdb", "application/x-msaccess")
            localHashMap.put("mdz", "audio/x-mod")
            localHashMap.put("me", "application/x-troff-me")
            localHashMap.put("mel", "text/x-vmel")
            localHashMap.put("mht", "message/rfc822")
            localHashMap.put("mhtml", "message/rfc822")
            localHashMap.put("mi", "application/x-mif")
            localHashMap.put("mid", "audio/mid")
            localHashMap.put("midi", "audio/midi")
            localHashMap.put("mif", "application/x-mif")
            localHashMap.put("mil", "image/x-cals")
            localHashMap.put("mio", "audio/x-mio")
            localHashMap.put("mmf", "application/x-skt-lbs")
            localHashMap.put("mng", "video/x-mng")
            localHashMap.put("mny", "application/x-msmoney")
            localHashMap.put("moc", "application/x-mocha")
            localHashMap.put("mocha", "application/x-mocha")
            localHashMap.put("mod", "audio/x-mod")
            localHashMap.put("mof", "application/x-yumekara")
            localHashMap.put("mol", "chemical/x-mdl-molfile")
            localHashMap.put("mop", "chemical/x-mopac-input")
            localHashMap.put("mov", "video/quicktime")
            localHashMap.put("movie", "video/x-sgi-movie")
            localHashMap.put("mp2", "video/mpeg")
            localHashMap.put("mp3", "audio/mpeg")
            localHashMap.put("mp4", "video/mp4")
            localHashMap.put("mpa", "video/mpeg")
            localHashMap.put("mpc", "application/vnd.mpohun.certificate")
            localHashMap.put("mpe", "video/mpeg")
            localHashMap.put("mpeg", "video/mpeg")
            localHashMap.put("mpg", "video/mpeg")
            localHashMap.put("mpg4", "video/mp4")
            localHashMap.put("mpga", "audio/mpeg")
            localHashMap.put("mpn", "application/vnd.mophun.application")
            localHashMap.put("mpp", "application/vnd.ms-project")
            localHashMap.put("mps", "application/x-mapserver")
            localHashMap.put("mpv2", "video/mpeg")
            localHashMap.put("mrl", "text/x-mrml")
            localHashMap.put("mrm", "application/x-mrm")
            localHashMap.put("ms", "application/x-troff-ms")
            localHashMap.put("msg", "application/vnd.ms-outlook")
            localHashMap.put("mts", "application/metastream")
            localHashMap.put("mtx", "application/metastream")
            localHashMap.put("mtz", "application/metastream")
            localHashMap.put("mvb", "application/x-msmediaview")
            localHashMap.put("mzv", "application/metastream")
            localHashMap.put("nar", "application/zip")
            localHashMap.put("nbmp", "image/nbmp")
            localHashMap.put("nc", "application/x-netcdf")
            localHashMap.put("ndb", "x-lml/x-ndb")
            localHashMap.put("ndwn", "application/ndwn")
            localHashMap.put("nif", "application/x-nif")
            localHashMap.put("nmz", "application/x-scream")
            localHashMap.put("nokia-op-logo", "image/vnd.nok-oplogo-color")
            localHashMap.put("npx", "application/x-netfpx")
            localHashMap.put("nsnd", "audio/nsnd")
            localHashMap.put("nva", "application/x-neva1")
            localHashMap.put("nws", "message/rfc822")
            localHashMap.put("oda", "application/oda")
            localHashMap.put("ogg", "audio/ogg")
            localHashMap.put("oom", "application/x-AtlasMate-Plugin")
            localHashMap.put("p10", "application/pkcs10")
            localHashMap.put("p12", "application/x-pkcs12")
            localHashMap.put("p7b", "application/x-pkcs7-certificates")
            localHashMap.put("p7c", "application/x-pkcs7-mime")
            localHashMap.put("p7m", "application/x-pkcs7-mime")
            localHashMap.put("p7r", "application/x-pkcs7-certreqresp")
            localHashMap.put("p7s", "application/x-pkcs7-signature")
            localHashMap.put("pac", "audio/x-pac")
            localHashMap.put("pae", "audio/x-epac")
            localHashMap.put("pan", "application/x-pan")
            localHashMap.put("pbm", "image/x-portable-bitmap")
            localHashMap.put("pcx", "image/x-pcx")
            localHashMap.put("pda", "image/x-pda")
            localHashMap.put("pdb", "chemical/x-pdb")
            localHashMap.put("pdf", "application/pdf")
            localHashMap.put("pfr", "application/font-tdpfr")
            localHashMap.put("pfx", "application/x-pkcs12")
            localHashMap.put("pgm", "image/x-portable-graymap")
            localHashMap.put("pict", "image/x-pict")
            localHashMap.put("pko", "application/ynd.ms-pkipko")
            localHashMap.put("pm", "application/x-perl")
            localHashMap.put("pma", "application/x-perfmon")
            localHashMap.put("pmc", "application/x-perfmon")
            localHashMap.put("pmd", "application/x-pmd")
            localHashMap.put("pml", "application/x-perfmon")
            localHashMap.put("pmr", "application/x-perfmon")
            localHashMap.put("pmw", "application/x-perfmon")
            localHashMap.put("png", "image/png")
            localHashMap.put("pnm", "image/x-portable-anymap")
            localHashMap.put("pnz", "image/png")
            localHashMap.put("pot,", "application/vnd.ms-powerpoint")
            localHashMap.put("ppm", "image/x-portable-pixmap")
            localHashMap.put("pps", "application/vnd.ms-powerpoint")
            localHashMap.put("ppt", "application/vnd.ms-powerpoint")
            localHashMap.put(
                "pptx",
                "application/vnd.openxmlformats-officedocument.presentationml.presentation"
            )
            localHashMap.put("pqf", "application/x-cprplayer")
            localHashMap.put("pqi", "application/cprplayer")
            localHashMap.put("prc", "application/x-prc")
            localHashMap.put("prf", "application/pics-rules")
            localHashMap.put("prop", "text/plain")
            localHashMap.put("proxy", "application/x-ns-proxy-autoconfig")
            localHashMap.put("ps", "application/postscript")
            localHashMap.put("ptlk", "application/listenup")
            localHashMap.put("pub", "application/x-mspublisher")
            localHashMap.put("pvx", "video/x-pv-pvx")
            localHashMap.put("qcp", "audio/vnd.qcelp")
            localHashMap.put("qt", "video/quicktime")
            localHashMap.put("qti", "image/x-quicktime")
            localHashMap.put("qtif", "image/x-quicktime")
            localHashMap.put("r3t", "text/vnd.rn-realtext3d")
            localHashMap.put("ra", "audio/x-pn-realaudio")
            localHashMap.put("ram", "audio/x-pn-realaudio")
            localHashMap.put("rar", "application/octet-stream")
            localHashMap.put("ras", "image/x-cmu-raster")
            localHashMap.put("rc", "text/plain")
            localHashMap.put("rdf", "application/rdf+xml")
            localHashMap.put("rf", "image/vnd.rn-realflash")
            localHashMap.put("rgb", "image/x-rgb")
            localHashMap.put("rlf", "application/x-richlink")
            localHashMap.put("rm", "audio/x-pn-realaudio")
            localHashMap.put("rmf", "audio/x-rmf")
            localHashMap.put("rmi", "audio/mid")
            localHashMap.put("rmm", "audio/x-pn-realaudio")
            localHashMap.put("rmvb", "audio/x-pn-realaudio")
            localHashMap.put("rnx", "application/vnd.rn-realplayer")
            localHashMap.put("roff", "application/x-troff")
            localHashMap.put("rp", "image/vnd.rn-realpix")
            localHashMap.put("rpm", "audio/x-pn-realaudio-plugin")
            localHashMap.put("rt", "text/vnd.rn-realtext")
            localHashMap.put("rte", "x-lml/x-gps")
            localHashMap.put("rtf", "application/rtf")
            localHashMap.put("rtg", "application/metastream")
            localHashMap.put("rtx", "text/richtext")
            localHashMap.put("rv", "video/vnd.rn-realvideo")
            localHashMap.put("rwc", "application/x-rogerwilco")
            localHashMap.put("s3m", "audio/x-mod")
            localHashMap.put("s3z", "audio/x-mod")
            localHashMap.put("sca", "application/x-supercard")
            localHashMap.put("scd", "application/x-msschedule")
            localHashMap.put("sct", "text/scriptlet")
            localHashMap.put("sdf", "application/e-score")
            localHashMap.put("sea", "application/x-stuffit")
            localHashMap.put("setpay", "application/set-payment-initiation")
            localHashMap.put("setreg", "application/set-registration-initiation")
            localHashMap.put("sgm", "text/x-sgml")
            localHashMap.put("sgml", "text/x-sgml")
            localHashMap.put("sh", "application/x-sh")
            localHashMap.put("shar", "application/x-shar")
            localHashMap.put("shtml", "magnus-internal/parsed-html")
            localHashMap.put("shw", "application/presentations")
            localHashMap.put("si6", "image/si6")
            localHashMap.put("si7", "image/vnd.stiwap.sis")
            localHashMap.put("si9", "image/vnd.lgtwap.sis")
            localHashMap.put("sis", "application/vnd.symbian.install")
            localHashMap.put("sit", "application/x-stuffit")
            localHashMap.put("skd", "application/x-Koan")
            localHashMap.put("skm", "application/x-Koan")
            localHashMap.put("skp", "application/x-Koan")
            localHashMap.put("skt", "application/x-Koan")
            localHashMap.put("slc", "application/x-salsa")
            localHashMap.put("smd", "audio/x-smd")
            localHashMap.put("smi", "application/smil")
            localHashMap.put("smil", "application/smil")
            localHashMap.put("smp", "application/studiom")
            localHashMap.put("smz", "audio/x-smd")
            localHashMap.put("snd", "audio/basic")
            localHashMap.put("spc", "application/x-pkcs7-certificates")
            localHashMap.put("spl", "application/futuresplash")
            localHashMap.put("spr", "application/x-sprite")
            localHashMap.put("sprite", "application/x-sprite")
            localHashMap.put("sdp", "application/sdp")
            localHashMap.put("spt", "application/x-spt")
            localHashMap.put("src", "application/x-wais-source")
            localHashMap.put("sst", "application/vnd.ms-pkicertstore")
            localHashMap.put("stk", "application/hyperstudio")
            localHashMap.put("stl", "application/vnd.ms-pkistl")
            localHashMap.put("stm", "text/html")
            localHashMap.put("svg", "image/svg+xml")
            localHashMap.put("sv4cpio", "application/x-sv4cpio")
            localHashMap.put("sv4crc", "application/x-sv4crc")
            localHashMap.put("svf", "image/vnd")
            localHashMap.put("svh", "image/svh")
            localHashMap.put("svr", "x-world/x-svr")
            localHashMap.put("swf", "application/x-shockwave-flash")
            localHashMap.put("swfl", "application/x-shockwave-flash")
            localHashMap.put("t", "application/x-troff")
            localHashMap.put("tad", "application/octet-stream")
            localHashMap.put("talk", "text/x-speech")
            localHashMap.put("tar", "application/x-tar")
            localHashMap.put("taz", "application/x-tar")
            localHashMap.put("tbp", "application/x-timbuktu")
            localHashMap.put("tbt", "application/x-timbuktu")
            localHashMap.put("tcl", "application/x-tcl")
            localHashMap.put("tex", "application/x-tex")
            localHashMap.put("texi", "application/x-texinfo")
            localHashMap.put("texinfo", "application/x-texinfo")
            localHashMap.put("tgz", "application/x-compressed")
            localHashMap.put("thm", "application/vnd.eri.thm")
            localHashMap.put("tif", "image/tiff")
            localHashMap.put("tiff", "image/tiff")
            localHashMap.put("tki", "application/x-tkined")
            localHashMap.put("tkined", "application/x-tkined")
            localHashMap.put("toc", "application/toc")
            localHashMap.put("toy", "image/toy")
            localHashMap.put("tr", "application/x-troff")
            localHashMap.put("trk", "x-lml/x-gps")
            localHashMap.put("trm", "application/x-msterminal")
            localHashMap.put("tsi", "audio/tsplayer")
            localHashMap.put("tsp", "application/dsptype")
            localHashMap.put("tsv", "text/tab-separated-values")
            localHashMap.put("ttf", "application/octet-stream")
            localHashMap.put("ttz", "application/t-time")
            localHashMap.put("txt", "text/plain")
            localHashMap.put("uls", "text/iuls")
            localHashMap.put("ult", "audio/x-mod")
            localHashMap.put("ustar", "application/x-ustar")
            localHashMap.put("uu", "application/x-uuencode")
            localHashMap.put("uue", "application/x-uuencode")
            localHashMap.put("vcd", "application/x-cdlink")
            localHashMap.put("vcf", "text/x-vcard")
            localHashMap.put("vdo", "video/vdo")
            localHashMap.put("vib", "audio/vib")
            localHashMap.put("viv", "video/vivo")
            localHashMap.put("vivo", "video/vivo")
            localHashMap.put("vmd", "application/vocaltec-media-desc")
            localHashMap.put("vmf", "application/vocaltec-media-file")
            localHashMap.put("vmi", "application/x-dreamcast-vms-info")
            localHashMap.put("vms", "application/x-dreamcast-vms")
            localHashMap.put("vox", "audio/voxware")
            localHashMap.put("vqe", "audio/x-twinvq-plugin")
            localHashMap.put("vqf", "audio/x-twinvq")
            localHashMap.put("vql", "audio/x-twinvq")
            localHashMap.put("vre", "x-world/x-vream")
            localHashMap.put("vrml", "x-world/x-vrml")
            localHashMap.put("vrt", "x-world/x-vrt")
            localHashMap.put("vrw", "x-world/x-vream")
            localHashMap.put("vts", "workbook/formulaone")
            localHashMap.put("wav", "audio/x-wav")
            localHashMap.put("wax", "audio/x-ms-wax")
            localHashMap.put("wbmp", "image/vnd.wap.wbmp")
            localHashMap.put("wcm", "application/vnd.ms-works")
            localHashMap.put("wdb", "application/vnd.ms-works")
            localHashMap.put("web", "application/vnd.xara")
            localHashMap.put("wi", "image/wavelet")
            localHashMap.put("wis", "application/x-InstallShield")
            localHashMap.put("wks", "application/vnd.ms-works")
            localHashMap.put("wm", "video/x-ms-wm")
            localHashMap.put("wma", "audio/x-ms-wma")
            localHashMap.put("wmd", "application/x-ms-wmd")
            localHashMap.put("wmf", "application/x-msmetafile")
            localHashMap.put("wml", "text/vnd.wap.wml")
            localHashMap.put("wmlc", "application/vnd.wap.wmlc")
            localHashMap.put("wmls", "text/vnd.wap.wmlscript")
            localHashMap.put("wmlsc", "application/vnd.wap.wmlscriptc")
            localHashMap.put("wmlscript", "text/vnd.wap.wmlscript")
            localHashMap.put("wmv", "audio/x-ms-wmv")
            localHashMap.put("wmx", "video/x-ms-wmx")
            localHashMap.put("wmz", "application/x-ms-wmz")
            localHashMap.put("wpng", "image/x-up-wpng")
            localHashMap.put("wps", "application/vnd.ms-works")
            localHashMap.put("wpt", "x-lml/x-gps")
            localHashMap.put("wri", "application/x-mswrite")
            localHashMap.put("wrl", "x-world/x-vrml")
            localHashMap.put("wrz", "x-world/x-vrml")
            localHashMap.put("ws", "text/vnd.wap.wmlscript")
            localHashMap.put("wsc", "application/vnd.wap.wmlscriptc")
            localHashMap.put("wv", "video/wavelet")
            localHashMap.put("wvx", "video/x-ms-wvx")
            localHashMap.put("wxl", "application/x-wxl")
            localHashMap.put("x-gzip", "application/x-gzip")
            localHashMap.put("xaf", "x-world/x-vrml")
            localHashMap.put("xar", "application/vnd.xara")
            localHashMap.put("xbm", "image/x-xbitmap")
            localHashMap.put("xdm", "application/x-xdma")
            localHashMap.put("xdma", "application/x-xdma")
            localHashMap.put("xdw", "application/vnd.fujixerox.docuworks")
            localHashMap.put("xht", "application/xhtml+xml")
            localHashMap.put("xhtm", "application/xhtml+xml")
            localHashMap.put("xhtml", "application/xhtml+xml")
            localHashMap.put("xla", "application/vnd.ms-excel")
            localHashMap.put("xlc", "application/vnd.ms-excel")
            localHashMap.put("xll", "application/x-excel")
            localHashMap.put("xlm", "application/vnd.ms-excel")
            localHashMap.put("xls", "application/vnd.ms-excel")
            localHashMap.put(
                "xlsx",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            )
            localHashMap.put("xlt", "application/vnd.ms-excel")
            localHashMap.put("xlw", "application/vnd.ms-excel")
            localHashMap.put("xm", "audio/x-mod")
            localHashMap.put("xml", "text/plain")
            localHashMap.put("xml", "application/xml")
            localHashMap.put("xmz", "audio/x-mod")
            localHashMap.put("xof", "x-world/x-vrml")
            localHashMap.put("xpi", "application/x-xpinstall")
            localHashMap.put("xpm", "image/x-xpixmap")
            localHashMap.put("xsit", "text/xml")
            localHashMap.put("xsl", "text/xml")
            localHashMap.put("xul", "text/xul")
            localHashMap.put("xwd", "image/x-xwindowdump")
            localHashMap.put("xyz", "chemical/x-pdb")
            localHashMap.put("yz1", "application/x-yz1")
            localHashMap.put("z", "application/x-compress")
            localHashMap.put("zac", "application/x-zaurus-zac")
            localHashMap.put("zip", "application/zip")
            var str: String? = localHashMap.get(paramString)
            if (str == null)
                str = "application/octet-stream"
            return str
        }
    }
}