#set($lastSegment = $PACKAGE_NAME.substring($PACKAGE_NAME.lastIndexOf(".") + 1))
#set($PACKAGE_NAME_SECTION = "${PACKAGE_NAME}.section")
#set($SCREEN = $lastSegment.substring(0,1).toUpperCase() + $lastSegment.substring(1))

#parse("Section.kt")