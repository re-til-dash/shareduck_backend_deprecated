function getOS() {
  let userAgent = window.navigator.userAgent;
  let platform = window.navigator.platform;
  let macosPlatforms = ["Macintosh", "MacIntel", "MacPPC", "Mac68K"];
  let windowsPlatforms = ["Win32", "Win64", "Windows", "WinCE"];
  let os = null;

  if (macosPlatforms.indexOf(platform) !== -1) {
    os = "Mac";
  } else if (windowsPlatforms.indexOf(platform) !== -1) {
    os = "Windows";
  } else {
    os = "Disable";
  }

  return os;
}

const downloadButton = document.querySelector("#download");

const os = getOS();

window.addEventListener("load", () => {
  downloadButton.textContent = `Download for ${os}`;
  
});
