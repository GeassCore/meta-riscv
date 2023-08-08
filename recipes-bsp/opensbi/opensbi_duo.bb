FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"


SRC_URI:milkv-duo-cv1800b = "git://github.com/starfive-tech/opensbi;branch=JH7110_VisionFive2_devel;protocol=https"
SRC_URI:append:milkv-duo-cv1800b = "\
	file://visionfive2-uboot-fit-image.its \
	"

DEPENDS:append:milkv-duo-cv1800b = " u-boot-tools-native dtc-native"
EXTRA_OEMAKE:append:milkv-duo-cv1800b = " FW_TEXT_START=0x40000000"

do_deploy:append:milkv-duo-cv1800b() {
	install -m 0644 ${WORKDIR}/visionfive2-uboot-fit-image.its ${DEPLOYDIR}/visionfive2-uboot-fit-image.its
	cd ${DEPLOYDIR}
	mkimage -f visionfive2-uboot-fit-image.its -A riscv -O u-boot -T firmware visionfive2_fw_payload.img
}
