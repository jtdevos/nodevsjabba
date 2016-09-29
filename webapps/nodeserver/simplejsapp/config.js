const os = require('os');
const process = require('process');

module.exports = (() => {
    var cfg = {};

    cfg.outfilesDir = process.env.OUTFILESDIR;
    if(!cfg.outfilesDir) {
        cfg.outfilesDir = os.tmpdir();
    }

    return cfg;
})();
