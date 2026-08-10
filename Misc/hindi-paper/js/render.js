function renderItem(item) {
  if (item.type === "tick") {
    return (
      '<li class="item-tick">' +
        '<span class="item-text">' + item.text + "</span>" +
        '<span class="tick-box">[&nbsp;&nbsp;&nbsp;&nbsp;]</span>' +
      "</li>"
    );
  }

  if (item.type === "answer") {
    var lineClass = item.wide ? " answer-line answer-line--wide" : " answer-line";
    return (
      '<li class="item-answer">' +
        '<span class="item-text">' + item.text + "</span>" +
        '<span class="answer-sep">—</span>' +
        '<span class="' + lineClass.trim() + '"></span>' +
      "</li>"
    );
  }

  if (item.type === "blank") {
    return '<li class="item-blank">' + item.text + "</li>";
  }

  if (item.options && item.options.length) {
    var options = item.options
      .map(function (o) {
        return "<li>" + o + "</li>";
      })
      .join("");

    return (
      "<li>" +
      item.text +
      '<ul class="option-list">' +
      options +
      "</ul></li>"
    );
  }

  return "<li>" + item.text + "</li>";
}

function renderHeader(config) {
  return (
    '<header class="paper-header">' +
      '<div class="paper-header__row">' +
        '<div class="paper-header__logo">' +
          '<img src="' + config.logo + '" alt="School logo" width="80" height="80">' +
        "</div>" +
        '<div class="paper-header__titles">' +
          "<div>" + config.school + "</div>" +
          "<div>" + config.classLine + "</div>" +
          "<div>" + config.assessment + "</div>" +
        "</div>" +
        '<div class="paper-header__stamp" aria-hidden="true">' +
          '<div class="stamp-circle"></div>' +
        "</div>" +
      "</div>" +
      '<div class="paper-header__meta">' +
        '<div class="meta-row">' +
          '<div class="meta-field meta-field--wide">' +
            '<span class="meta-label">Name:</span>' +
            '<span class="meta-line"></span>' +
          "</div>" +
          '<div class="meta-static">Class: ' + config.classNumber + "</div>" +
          '<div class="meta-static meta-static--right">Subject: ' + config.subject + "</div>" +
        "</div>" +
        '<div class="meta-row">' +
          '<div class="meta-field meta-field--short">' +
            '<span class="meta-label">Date:</span>' +
            '<span class="meta-line meta-line--short"></span>' +
          "</div>" +
          '<div class="meta-static meta-static--center">Total Marks: ' + config.totalMarks + "</div>" +
          '<div class="meta-static meta-static--right"></div>' +
        "</div>" +
      "</div>" +
    "</header>"
  );
}

function renderMatchingItem(item, side) {
  var isLeft = side === "left";
  var alt = item.label || "";
  var figure =
    '<figure class="match-figure">' +
      '<img src="' + item.image + '" alt="' + alt + '" class="match-img">' +
    "</figure>";
  var dot = '<span class="match-dot" aria-hidden="true"></span>';

  if (isLeft) {
    return (
      '<div class="match-item match-item--left">' +
        figure +
        dot +
      "</div>"
    );
  }

  return (
    '<div class="match-item match-item--right">' +
      dot +
      figure +
    "</div>"
  );
}

function renderMatching(section) {
  var rows = section.animals.length;
  var html = '<div class="match-grid">';

  for (var i = 0; i < rows; i++) {
    html += renderMatchingItem(section.animals[i], "left");
    html += '<div class="match-gap" aria-hidden="true"></div>';
    html += renderMatchingItem(section.habitats[i], "right");
  }

  html += "</div>";
  return html;
}

function renderAnswerBlocks(section) {
  var blocks = section.answerBlocks || 3;
  var lines = section.linesPerBlock || 3;
  var html = '<div class="answer-blocks">';

  for (var b = 0; b < blocks; b++) {
    html += '<div class="answer-block">';
    html += '<div class="answer-block__label">उत्तर</div>';
    html += '<div class="answer-block__lines">';
    for (var l = 0; l < lines; l++) {
      html += '<div class="answer-block__line"></div>';
    }
    html += "</div></div>";
  }

  html += "</div>";
  return html;
}

function renderLabeledQuestions(items) {
  var html = '<ul class="question-list question-list--labeled">';
  items.forEach(function (item, index) {
    html +=
      '<li class="item-labeled">' +
        '<span class="q-label">प्रश्न-' + (index + 1) + "</span> " +
        item.text +
      "</li>";
  });
  html += "</ul>";
  return html;
}

function renderSection(section) {
  var longClass = section.long ? " section--long" : "";
  var body = "";

  if (section.type === "matching") {
    body = renderMatching(section);
  } else if (section.type === "qa-blocks") {
    body = renderLabeledQuestions(section.items) + renderAnswerBlocks(section);
  } else if (section.items && section.items.length > 0) {
    body =
      '<ul class="question-list">' +
      section.items.map(renderItem).join("") +
      "</ul>";
  }

  return (
    '<section class="section' + longClass + '" data-section="' + section.id + '">' +
      '<div class="section-head">' +
        '<h2 class="section-head__title">' + section.id + ". " + section.title + "</h2>" +
        '<span class="section-head__marks">' + section.marks + "</span>" +
      "</div>" +
      body +
    "</section>"
  );
}

function renderPaper(root, config) {
  config = config || PAPER_CONFIG;

  root.innerHTML =
    '<div class="page-frame" aria-hidden="true"></div>' +
    '<div class="paper">' +
      renderHeader(config) +
      '<main class="paper-body">' +
        config.sections.map(renderSection).join("") +
      "</main>" +
    "</div>";
}
